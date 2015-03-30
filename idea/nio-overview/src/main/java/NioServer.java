import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by pixel on 3/19/15.
 */
public class NioServer {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel
                .open(AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(),1));
        serverChannel.bind(new InetSocketAddress("localhost",8080));
        serverChannel.accept(null, (CompletionHandlerImpl) (result, attachement) -> {
            System.out.println("CHANNEL " + result);
        });
        Thread.sleep(20000);
    }
}

