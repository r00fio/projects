package algorithm;

import edu.princeton.cs.introcs.StdDraw;
import org.openjdk.jmh.annotations.Benchmark;

/**
 * Created by pixel on 4/8/15.
 */
public class FibonacciSequence {
    public static int[] compute(int hi) {
        int[] res = new int[hi];
        res[0] = 0;
        res[1] = 1;

        for (int i = 2; i < hi; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }
        return res;
    }
}
