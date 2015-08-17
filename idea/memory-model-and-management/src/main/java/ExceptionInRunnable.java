import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by air on 29/07/15.
 */
public class ExceptionInRunnable {
    public static void main(String[] args) {
        withFutureTask();
    }

    private static FutureTask withFutureTask() {
        FutureTask<Object> task = new FutureTask<Object>(() -> {
            doThrow();
            return null;
        });
        new Thread(task).start();
        try {
            task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                System.out.println("Caught my RuntimeException");
            }
        }
        return task;
    }

    private static void withRunnable() {
        Thread t = new Thread(() -> {
            doThrow();
        });
        try {
            t.start();
        } catch (RuntimeException e) {
            System.out.println("caught");
        }
    }

    private static void doThrow() {
        System.out.println("Started");
        throw new RuntimeException("dadsad");
    }
}
