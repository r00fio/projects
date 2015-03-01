import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bebe on 2/18/15.
 */
public class CustomCompletionHandlerPipeline extends ArrayBlockingQueue<AsynchronousSocketChannel> {

    public CustomCompletionHandlerPipeline(int capacity, AsynchronousSocketChannel initChannel) {
        super(capacity);
        offer(initChannel);
    }

    private final ConcurrentHashMap<String, ByteBuffer> responseCache = new ConcurrentHashMap<>(15);

    private <V> void completeReadResponse(V bytesRead
            , Attachment<ByteBuffer, Attachment<String, AsynchronousSocketChannel>> attachment) {
        offer(attachment.getB().getB());
        System.out.println(bytesRead);
        responseCache.put(attachment.getB().getA(), attachment.getA());

//        System.out.println("complete read" + Thread.currentThread().getName() + " " + Thread.currentThread().getThreadGroup());
//            System.out.println(new String(attachment.getA().array()
//                    , Charset.forName("UTF-8")));
    }

    private <V> void readResponse(V bytesWritten, Attachment<String,AsynchronousSocketChannel> attachment) {
//        System.out.println("read response " + Thread.currentThread().getName()
//                + " " + Thread.currentThread().getThreadGroup());

        ByteBuffer allocate = ByteBuffer.allocate(500240);
        final Attachment nextAttachment = new Attachment(allocate, attachment);
        attachment.getB().read(allocate, nextAttachment
                , (CustomCompletionHandler<Integer, Attachment>)
                this::completeReadResponse);
    }

    public <V> void request(V result, Attachment<String, AsynchronousSocketChannel> attachment) {
//        System.out.println("request" + Thread.currentThread().getName() + " " + Thread.currentThread().getThreadGroup());

        try {
            AsynchronousSocketChannel channel = attachment.getB();
            channel.write(RequestBuilder.buildGetRequest(attachment.getA())
                    , attachment
                    , (CustomCompletionHandler<Integer, Attachment>)
                    this::readResponse);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public ByteBuffer response(String request){
        return responseCache.get(request);
    }

    /**
     * Extended to support lambda and default method and provide
     * default functionality
     *
     * @param <V>
     * @param <A>
     */
    interface CustomCompletionHandler<V, A> extends CompletionHandler<V, A> {

        @Override
        default void failed(Throwable exc, A attachment) {
            System.out.println(exc);
        }
    }

}
