package concurrent.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by air on 29/07/15.
 * Wait vs park
 *
 * Specification of Future is that task lifecycle can only move forwards,
 * not backwards just like the ExecutorService lifecycle
 *
 */
public class FutureGetBlocks {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Object> futureTask = new FutureTask(() -> {
            System.out.println("Exec " + Thread.currentThread().getName());
            return null;
        });
        new Thread(futureTask).start();
        new Thread(futureTask).start();
        new Thread(futureTask).start();
        new Thread(futureTask).start();
        new Thread(futureTask).start();
        new Thread(futureTask).start();

        futureTask.get();

    }
}
