import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.*;

/**
 * Created by bebe on 2/5/15.
 * In short AsyncFileChannel can read in many threads
 * By default it creates 3 threads but operations connect read and write
 * called within 1 thread because only 1 client
 * in b group
 * LinuxAsynchronousChannelProvider.defaultEventPort(ThreadPool.defaultThreadPool().start)
 * by default cachedTHreadPool with threadsQty = availableProcessor
 * 1 Why do i need more then 1 thread if i can't write on 1 schannel concurrentelly
 * 2 what is Channel is it 1 for hot:port endpoint or can be many
 * by thread on handler
 */

public class AsynchronousParallelClientSocket {
    private static CustomCompletionHandlerPipeline pipeline;
            static{
                try {
                    pipeline = new CustomCompletionHandlerPipeline(20, createNewChannel());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    public ByteBuffer response(String request){
        return pipeline.response(request);
    }

    public void write(String remoteUrl) throws IOException, InterruptedException, ExecutionException, URISyntaxException {
        AsynchronousSocketChannel channel = pipeline.poll();

        if (channel == null) {
            channel = createNewChannel();
        }
        URI remote = new URI(remoteUrl);
        Attachment<String, AsynchronousSocketChannel> attachment
                = new Attachment<>(remoteUrl, channel);
        try {
            channel.connect(new InetSocketAddress(remote.getHost(), remote.getPort() ==-1 ? 80:remote.getPort()), attachment
                    , (CustomCompletionHandlerPipeline.CustomCompletionHandler<Void, Attachment>)
                    pipeline::request);
        } catch (AlreadyConnectedException e) {
            pipeline.request(0, attachment);
        }
    }
    /**
     * can specify my execThreadPool but it will be used for handlers
     * and will be created 1 thread on each new open call
     * 12 best for 1600 clients
     */

    private static AsynchronousSocketChannel createNewChannel() throws IOException {
        return AsynchronousSocketChannel.open(
                AsynchronousChannelGroup.withFixedThreadPool(12, new ThreadFactoryBuilder().build()));
    }

}
