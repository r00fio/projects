package client.stage.v2;

import client.Attachment;
import client.stage.CompletionStage;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.TimeUnit;

/**
 * Created by bebe on 3/6/15.
 */
public class WriteCompletionStage implements CompletionStage<Integer, Attachment<StringBuilder>> {


    private final int TMP_BUFFER_SIZE = 10000;
    private CompletionHandler prevStage;

    public WriteCompletionStage(CompletionHandler prevStage) {
        this.prevStage = prevStage;
    }

    /**
     * THE PROBLEM WHEN READ ENDS IT RETURNS UNAVAILABLE STATUS BUT MUST BE ENDOFFILE
     * @param bytesWritten
     * @param attachment
     */
    @Override
    public void completed(Integer bytesWritten, Attachment<StringBuilder> attachment) {
        if (bytesWritten > 0) {
            if (attachment.getBuffer() == null) {
                attachment.setBuffer(ByteBuffer.allocate(TMP_BUFFER_SIZE));
            }
            try {
                attachment.getChannel().read(attachment.getBuffer()
//                        , 50
//                        , TimeUnit.MILLISECONDS
                        , attachment
                        , new ReadCompletionStage(this)); // cache handler
            } catch (IllegalStateException e) {
                System.out.println("EXCEPTION");
                completed(1, attachment);
            }
        } else {
            prevStage.completed(null, attachment);
        }
    }
}
