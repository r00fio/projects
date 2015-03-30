package client;

import sun.nio.ch.Net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * Created by bebe on 2/5/15.
 * In short AsyncFileChannel can read in many threads
 * By default it creates 3 threads but operations connect read and write
 * called within 1 thread because only 1 client
 * in b group
 * LinuxAsynchronousChannelProvider.defaultEventPort(ThreadPool.defaultThreadPool().start)
 * by default cachedTHreadPool with threadsQty = availableProcessor
 * 1 Why do i need more then 1 thread if i can't write on 1 schannel concurrentelly
 * 2 what is Channel is it 1 for host:port endpoint or can be many
 * by thread on handler
 * i think channel is like a WORKER
 */

public class AsynchronousParallelClientSocket {
    private static CustomCompletionHandlerPipelineV2 pipeline;

    static {
        try {
            pipeline = new CustomCompletionHandlerPipelineV2(20000, createNewChannel());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String remoteUrl) throws IOException, InterruptedException, ExecutionException, URISyntaxException {
        AsynchronousSocketChannel channel = getChannel();
        Attachment attachment = new Attachment(pipeline)
                .setChannel(channel)
                .setUrl(remoteUrl)
                .setAccumulator(new StringBuilder());
        try {
            URI remote = new URI(remoteUrl);
            final InetSocketAddress inetSocketAddress = Net.checkAddress(
                    new InetSocketAddress(remote.getHost(), remote.getPort() == -1 ? 80 : remote.getPort()));
            if (channel.isOpen()) {
                channel.setOption(StandardSocketOptions.SO_RCVBUF, 10000);
                channel.setOption(StandardSocketOptions.SO_SNDBUF, 10000);
                channel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
                channel.connect(
                        inetSocketAddress
                        , attachment
                        , pipeline.STAGE);
            } else {
                pipeline.offer(channel);
                write(remoteUrl);
            }
        } catch (AlreadyConnectedException e) {
            pipeline.STAGE.completed(null, attachment);
        }
    }

    private AsynchronousSocketChannel getChannel() {
        AsynchronousSocketChannel channel = pipeline.poll();

        if (channel == null) {
            try {
                channel = createNewChannel();
            }catch (IOException exc){
//                System.out.println(exc);
                channel = getChannel();
            }
        }
        return channel;
    }

    /**
     * can specify my execThreadPool but it will be used for handlers
     * and will be created 1 thread on each new open call
     * 12 best for 1600 clients
     */
    static AsynchronousChannelGroup asynchronousChannelGroup;
    static {
        try {
            asynchronousChannelGroup = AsynchronousChannelGroup.withThreadPool(Executors.newCachedThreadPool());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static AsynchronousSocketChannel createNewChannel() throws IOException {
        return AsynchronousSocketChannel.open(asynchronousChannelGroup);
//        return AsynchronousSocketChannel.open(AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(1)));
//        return AsynchronousSocketChannel.open(
//                AsynchronousChannelGroup.withFixedThreadPool(12, new ThreadFactoryBuilder().build()));
    }

    public static void main(String[] args) throws InterruptedException {
        AsynchronousParallelClientSocket client = new AsynchronousParallelClientSocket();
//        Thread.currentThread().sleep(5000);
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                try {
                    client.write("http://localhost:8080");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.currentThread().sleep(150000);
    }
}
