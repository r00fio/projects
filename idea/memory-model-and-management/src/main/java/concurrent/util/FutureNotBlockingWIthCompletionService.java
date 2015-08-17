package concurrent.util;

import java.util.concurrent.*;

/**
 * Created by air on 02/08/15.
 */
public class FutureNotBlockingWIthCompletionService {
    static CompletionService<String> service = new ExecutorCompletionService<>(Executors.newCachedThreadPool());

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int a = i;
            service.submit(() -> {
                double d = delay();
                System.out.println("Future " + a + "started" + d);
                return "Future " + a + " started";
            });
        }
        try {
            Future<String> take = service.take();
            String result = take.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
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
