package concurrent.util;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by air on 03/08/15.
 */
public class FutureBlockingWithExecutorService {

    private static final ExecutorService service = Executors.newCachedThreadPool();
    public static void main(String[] args) {
        List<Future<String>> futures = null;
        for (int i = 0; i < 10; i++) {
            try {
                final int a = i;
                Callable<String> c = () -> {
                    double d = delay();
                    System.out.println("Future " + a + "started" + d);
                    return "Future " + a + " started";
                };
                futures = service.invokeAll(Lists.<Callable<String>>newArrayList(c));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    private static double delay() {
        double d = 0.3;
        for (int j = 0; j < 100000000; j++) {
            for (int k = 0; k < 10000; k++) {
                d = Math.sqrt(33.9 * 2 + 3 * 33);
            }
        }
        return d;
    }
}
