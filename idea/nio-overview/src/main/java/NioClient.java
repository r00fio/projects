import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/**
 * Created by pixel on 3/19/15.
 */
public class NioClient {
    private static final String message = "HELLO";
    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        ByteBuffer byteBuffer = ByteBuffer.allocate(message.length() * 2);
        byteBuffer.put(message.getBytes());
        socketChannel.connect(new InetSocketAddress("localhost",8080), null
                , (CompletionHandlerImpl) (bytes, atachment) -> {
            System.out.println("connected " + bytes);
            socketChannel.write(byteBuffer, null, (CompletionHandlerImpl) (bytesWritten, att) -> {
                System.out.println("bytes send " + bytesWritten);
            });
        });
        Thread.sleep(10000);
    }
}
