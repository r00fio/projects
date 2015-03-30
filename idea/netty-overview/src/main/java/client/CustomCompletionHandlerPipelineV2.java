package client;

import client.stage.v2.WriteCompletionStage;

import java.net.URISyntaxException;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ArrayBlockingQueue;

import client.stage.CompletionStage;

/**
 * Created by bebe on 3/6/15.
 * TODO : use stack instead of queue because of hot data can became cold
 */
public class CustomCompletionHandlerPipelineV2 extends ArrayBlockingQueue<AsynchronousSocketChannel> {

    public CustomCompletionHandlerPipelineV2(int capacity, AsynchronousSocketChannel initChannel) {
        super(capacity);
        offer(initChannel);
    }

    public final CompletionStage<Void, Attachment> STAGE = this::connectionCompleted;

    private void connectionCompleted(Void v, Attachment<StringBuilder> attachment) {
        try {
            attachment.getChannel().write(RequestBuilder.buildGetRequest(attachment.getUrl())
                    , attachment
                    , new WriteCompletionStage(STAGE)); // cache handler
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}