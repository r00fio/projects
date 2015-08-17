package executors;

import java.util.concurrent.Executor;

/**
 * Created by air on 31/07/15.
 */
public class ProducerConsummer {
    public static void main(String[] args) {

    }

    class ThreadPerTaskExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }
}
