package performance.test;

import algorithm.FibonacciSequence;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;

import static org.junit.Assert.assertArrayEquals;

/**
 * Created by 8lackC on 4/27/15.
 */
public class FibonacciSeqPerfTest {
//    @Test
    public void testCompute() throws Exception {
        int[] expected = new int[]{0,1,1,2,3,5,8,13,21};
        assertArrayEquals(expected, FibonacciSequence.compute(expected.length));
    }

    @Benchmark
    public static void main() {

    }
}
