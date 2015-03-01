package client;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.netty.buffer.UnpooledDirectByteBuf;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * Created by bebe on 2/18/15.
 */
public class CustomCompletionHandlerPipeline extends ArrayBlockingQueue<AsynchronousSocketChannel> {

    public CustomCompletionHandlerPipeline(int capacity, AsynchronousSocketChannel initChannel) {
        super(capacity);
        offer(initChannel);
    }

    private final ConcurrentHashMap<String, ByteBuffer> responseCache = new ConcurrentHashMap<>(15);
    private final CustomCompletionHandler<Integer, Attachment> READ_HANDLER = (CustomCompletionHandler<Integer, Attachment>)
            this::completeReadResponse;
    private final CustomCompletionHandler<Integer, Attachment> WRITE_HANDLER = (CustomCompletionHandler<Integer, Attachment>)
            this::readResponse;
    volatile int i = 0;

    private void completeReadResponse(int bytesRead, Attachment<ByteArrayDataOutput> attachment) {
        AsynchronousSocketChannel channel = attachment.getChannel();
        if (channelNotEmpty(bytesRead, attachment)) {
            // What if invoke directelly?
            i++;
            attachment.getAccumulator().write(attachment.getBuffer().array());
            readResponse(0, attachment);
//            attachment.setBuffer(ByteBuffer.allocate(7084));
//            attachment.getChannel().read(attachment.getBuffer()
//                    , attachment
//                    , READ_HANDLER); // cache handler
        } else {
            attachment.getLatch().countDown();
            offer(channel);
            attachment.getAccumulator().write(attachment.getBuffer().array());
            ByteBuffer response = ByteBuffer.wrap(attachment.getAccumulator().toByteArray());
            responseCache.put(attachment.getUrl(), response);
            if (!new String(response.array()).contains("a.i.aa,window.gbar.elr&&a" +
                    ".i.ca(window.gbar.elr()),window.gbar.elc&&window.gbar.elc(a.i.ca")) {
                throw new AssertionError();
            }
            System.out.println(i++);
        }
    }

    private boolean channelNotEmpty(int bytesRead, Attachment<ByteArrayDataOutput> attachment) {
        return bytesRead > 0;// || (bytesRead > 0 && attachment.getBuffer().hasRemaining());
    }

    private void readResponse(int bytesWritten, Attachment attachment) {
        attachment.setBuffer(ByteBuffer.allocate(7084));
        attachment.getChannel().read(attachment.getBuffer()
                , attachment
                , READ_HANDLER); // cache handler
//        try {
//            attachment.getLatch().await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public void request(Void s, Attachment attachment) {
        try {
            attachment.setLatch(new CountDownLatch(1));
            attachment.setAccumulator(ByteStreams.newDataOutput(1024));
            attachment.getChannel().write(RequestBuilder.buildGetRequest(attachment.getUrl())
                    , attachment
                    , WRITE_HANDLER); // cache handler
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public ByteBuffer response(String request) {
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
