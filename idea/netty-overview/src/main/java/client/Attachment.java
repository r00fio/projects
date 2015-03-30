package client;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by bebe on 2/18/15.
 */
public class Attachment<A> {
    private A Accumulator;
    private String url;
    private AsynchronousSocketChannel channel;
    private ByteBuffer buffer;
    private boolean readStarted;
    private Integer expectedContentLength;
    private Integer actualContentLength;
    private Integer bytesRead = 0;
    private StringBuilder content;
    private StringBuilder headers;
    private List<Integer> reads = new ArrayList<>();
    private ArrayBlockingQueue<AsynchronousSocketChannel> pipeline;
    public Attachment(ArrayBlockingQueue<AsynchronousSocketChannel> pipeline){
        this.pipeline = pipeline;
    }
    public void addRead(Integer i){
        reads.add(i);
    }
    public boolean getReadStarted() {
        return readStarted;
    }

    public void setReadStarted(boolean readStarted) {
        this.readStarted = readStarted;
    }

    public A getAccumulator() {
        return Accumulator;
    }

    public Attachment<A> setAccumulator(A accumulator) {
        this.Accumulator = accumulator;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Attachment<A> setUrl(String url) {
        this.url = url;
        return this;
    }

    public AsynchronousSocketChannel getChannel() {
        return channel;
    }

    public Attachment<A> setChannel(AsynchronousSocketChannel channel) {
        this.channel = channel;
        return this;
    }

    public ByteBuffer getBuffer() {
        return buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public Integer getExpectedContentLength() {
        return expectedContentLength;
    }

    public void setExpectedContentLength(Integer expectedContentLength) {
        this.expectedContentLength = expectedContentLength;
    }

    public StringBuilder getHeaders() {
        return headers;
    }

    public void setHeaders(StringBuilder headers) {
        this.headers = headers;
    }

    public StringBuilder getContent() {
        return content;
    }

    public void setContent(StringBuilder content) {
        this.content = content;
    }

    public Integer getActualContentLength() {
        return actualContentLength;
    }

    public void setActualContentLength(Integer actualContentLength) {
        this.actualContentLength = actualContentLength;
    }

    public Integer getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(Integer bytesRead) {
        this.bytesRead = bytesRead;
    }

    public List<Integer> getReads() {
        return reads;
    }

    public ArrayBlockingQueue<AsynchronousSocketChannel> getPipeline() {
        return pipeline;
    }
}
