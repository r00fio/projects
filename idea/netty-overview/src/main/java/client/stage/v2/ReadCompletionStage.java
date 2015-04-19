package client.stage.v2;

import client.Attachment;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by bebe on 3/6/15.
 */
public class ReadCompletionStage implements CompletionHandler<Integer, Attachment<StringBuilder>> {

    private CompletionHandler prevStage;

    public ReadCompletionStage(CompletionHandler prevStage) {
        this.prevStage = prevStage;
    }

    @Override
    public void completed(Integer bytesRead, Attachment<StringBuilder> attachment) {
        if (bytesRead > 0) {

            attachment.addRead(bytesRead);
            attachment.getAccumulator().append(new String(attachment.getBuffer().array()));
            attachment.getBuffer().flip();
            prevStage.completed(1, attachment);
        } else {
            continueRead(attachment);
        }
    }

    @Override
    public void failed(Throwable exc, Attachment<StringBuilder> attachment) {
            continueRead(attachment);
    }

    static AtomicInteger i = new AtomicInteger();

    public static void continueRead(Attachment<StringBuilder> attachment) {
        attachment.getPipeline().offer(attachment.getChannel());
        String s = attachment.getAccumulator().toString().trim();
        StringBuilder reads = new StringBuilder();
        attachment.getReads().forEach((integer) -> reads.append(",").append(integer));
        System.out.println(s.length() + " " + i.incrementAndGet() + " " + s.equals(b) + "  " + reads.toString() + " size " + attachment.getPipeline().size());
        //+ (s.equals(b) ? "" : new StringBuilder("\n" + s + "\n" + b)));
    }

    static String b = "HTTP/1.1 200 OK\r\nContent-Length: 18460\r\nConnection: keep-alive\r\n\r\nvCONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1CONTENT1END";

}