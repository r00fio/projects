import com.mongodb.*;
import com.mongodb.util.JSON;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.util.concurrent.DefaultEventExecutorGroup;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.StampedLock;

import static io.netty.handler.codec.http.HttpResponseStatus.*;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Created by bebe on 2/15/15.
 * MONGODB IS OBJECT MAPPING OUT OF THE BOX - MAIN FEATURE!
 */
public class Handler extends DefaultHandler<FullHttpRequest> {
    private final EventExecutorGroup executorGroup;
    private final String URL_BASE = "http://pixabay.com/api/?username=yury_grybkov&key=b7d42885f83d1f99e37b";
    final static String QUERY_COLLECTION_NAME = "q";

    MongoClient connectionsPool;
    public Handler() throws UnknownHostException {
        executorGroup = new DefaultEventExecutorGroup(5);
        connectionsPool = new MongoClient();
    }
//jmh
    @Override
    public void messageReceived(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        Map<String, List<String>> parameters = new QueryStringDecoder(req.getUri()).parameters();
        StringBuilder queryBuilder = new StringBuilder(URL_BASE);
        final String query = parameters.containsKey(QUERY_COLLECTION_NAME) ? parameters.get(QUERY_COLLECTION_NAME).get(0) : null ;

        if (query != null && !query.isEmpty()) {
            queryBuilder.append("&q=").append(query);
            if (parameters.containsKey("image_type")) {
                queryBuilder.append("&image_type=")
                        .append(parameters.get("image_type").get(0));
            }
        }else sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));

        Future<String> futureTask = executorGroup.submit(new GetImageTask(
                new URL(queryBuilder.toString())));

        futureTask.addListener(future -> {
            if (future.isSuccess()) {
                String serviceResponse = future.get().toString();
                DB db = connectionsPool.getDB("pixabu_image_search");

                DBCollection car = db.collectionExists(query) ? db.getCollection(query) :
                        db.createCollection(query, new BasicDBObject());
                Object parse = JSON.parse(serviceResponse);
                car.insert((DBObject) parse);
                sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, OK
                        , Unpooled.copiedBuffer(serviceResponse.getBytes())));
            } else {
                ctx.fireExceptionCaught(future.cause());
            }
        });
    }

    private static class GetImageTask implements Callable<String> {
        //                String spec = "http://www.google.com";
        private final URL url;

        private GetImageTask(URL url) {
            this.url = url;
        }

        @Override
        public String call() {
            StringBuilder inputLine = new StringBuilder();
            try {
                URLConnection connection = url.openConnection();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    inputLine.append(line);
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return inputLine.toString();
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient();
        DB db = mongoClient.getDB("pixabu_image_search");
        DBCollection car;
        String collectionName = "car";
        if (!db.collectionExists(collectionName)) {
            car = db.createCollection(collectionName, new BasicDBObject());
        } else car = db.getCollection(collectionName);
//        Object parse = JSON.parse(s);
//        car.insert((DBObject)parse);
        System.out.println();
    }
}
