import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

/**
 * Created by bebe on 2/19/15.
 */
public class AsynClientTest {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException, ExecutionException {
        System.out.println(Thread.currentThread().getName());
        final InetSocketAddress remote = new InetSocketAddress("localhost", 8080);
        AsynchronousParallelClientSocket clientSocket = new AsynchronousParallelClientSocket();
        for (int i = 0; i < 200; i++) {
//            System.out.println(i);
            clientSocket.write("http://www.google.com:80");
        }
    }
}
