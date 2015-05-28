import algorithm.FibonacciSequence;
import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciSequenceTest {

    @Test
    public void testCompute() throws Exception {
        int[] expected = new int[]{0,1,1,2,3,5,8,13,21};
        assertArrayEquals(expected, FibonacciSequence.compute(expected.length));
    }
}