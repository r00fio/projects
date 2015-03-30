package concurrent.util;

import java.util.concurrent.*;

/**
 * Created by bebe on 3/4/15.
 */
public class TestCompletionService {
    private static final CompletionService completionService =
            new ExecutorCompletionService(Executors.newFixedThreadPool(5));

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        for (int i = 0; i < 20; i++) {
            int a = i;
            new Thread(() -> {
                completionService.submit(() -> "setter thread " + a);
                for (; ; ) {
                    try {
                        Future take = completionService.take();
                        Object result = take.get();
                        System.out.println((take != null ? result : "null") + " == getter thread " + a + " = " + result.equals("setter thread " + a));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }

    }
}
