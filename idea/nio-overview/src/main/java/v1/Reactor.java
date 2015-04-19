package v1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by pixel on 4/2/15.
 */
public class Reactor {

    public void start(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        new Thread(() -> {
            try {
                for (; ; ) {
                    selector.select();
                    for (Iterator<SelectionKey> i = selector.selectedKeys().iterator(); i.hasNext(); ) {
                        i.next();
                        i.remove();
                        SocketChannel acceptChannel = serverSocketChannel.accept();
                        if (acceptChannel != null) { // Channel in non-blocking mode => can return null if no con avail
                            acceptChannel.configureBlocking(false);
                            SelectionKey key = acceptChannel.register(selector, SelectionKey.OP_READ);
//                        selector.wakeup(); uncomment if selector.select and ch.accept in dif threads
                            if (key.isReadable()) {
                                System.out.println("readable");
                            }
                            if (key.isWritable()) {
                                System.out.println("writable");
                            }
                            if (key.isAcceptable()) {
                                System.out.println("acceptable");
                            }
                            if (key.isConnectable()) {
                                System.out.println("Connectable");
                            }
                        }

                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
