import client.AsynchronousParallelClientSocket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

/**
 * Created by bebe on 2/19/15.
 */
public class ClientTest {
    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException, ExecutionException {
        System.out.println(Thread.currentThread().getName());
        final InetSocketAddress remote = new InetSocketAddress("localhost", 8080);
        AsynchronousParallelClientSocket clientSocket = new AsynchronousParallelClientSocket();
        for (int i = 0; i < 100; i++) {
//            System.out.println(i);
            clientSocket.write("http://www.google.com:80");
//            clientSocket.write("http://localhost:8080");
        }
    }
}
