import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.pushtorefresh.javac_warning_annotation.Warning;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;

/**
 * Created by bebe on 1/11/15.
 */
@Warning("TODO")
public class Main {
    public static void translate(LongEvent event, long sequence, ByteBuffer buffer) {
        event.set(buffer.getLong(0));
    }

    public static void main(String[] args) throws InterruptedException {
        int ringBufferSize = 1024;
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, ringBufferSize, Executors.newCachedThreadPool());
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event - " + event));
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            ringBuffer.<ByteBuffer>publishEvent(Main::translate, bb);
            Thread.sleep(1000);
        }
    }
}

class LongEvent {
    private long value;

    public void set(long value) {
        this.value = value;
    }
}

class LongEventProducer {
    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer bb) {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try {
            LongEvent event = ringBuffer.get(sequence); // Get the entry in the Disruptor
            // for the sequence
            event.set(bb.getLong(0));  // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
