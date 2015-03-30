package concurrent.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bebe on 3/4/15.
 * Asynchronous means - execute not in caller thread
 * if ((t = Thread.currentThread()) instanceof ForkJoinWorkerThread) --- note
 *
 */
public class ExecutorSubmitVsExecute {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(()->{
            System.out.println("htststas");});
    }
}
