package concurrent.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by air on 02/08/15.
 */
public class FutureNotBlockingGeg {
    static ExecutorService executor = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int a = i;
            Future<String> submit = executor.submit(() -> {
                double d = delay();
                System.out.println("Future " + a + "started" + d);
                return "Future " + a + " started";
            });
            try {
                submit.get(0, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
//                e.printStackTrace();
                // task continue to run. And will finish normally
                // handle here timeout
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
