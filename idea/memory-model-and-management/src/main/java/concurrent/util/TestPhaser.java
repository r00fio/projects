package concurrent.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Phaser;

/**
 * Created by bebe on 3/4/15.
 * Phasers may also be used by tasks executing in
 * a ForkJoinPool, which will ensure sufficient parallelism to execute
 * tasks when others are blocked waiting for a phase to advance.
 */
public class TestPhaser {
    public static void main(String[] args) {
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int a = i;
            tasks.add(()-> System.out.println(a));
        }
        runTasks(tasks, 1);
    }
    private static void runTasks(List<Runnable> tasks, int iterations) {
        Phaser phaser = new Phaser(1) {
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("phase = " + phase + " registered parties = " + registeredParties);
                return phase >= iterations || registeredParties == 0;
            }
        };
        // create and start threads
        tasks.forEach(task -> {
            phaser.register();
            new Thread() {
                public void run() {
                    do {
                        System.out.println("arrive done. Waiting for last arrival...");
                        phaser.arriveAndAwaitAdvance(); // await all creation
                        task.run();
                    }while (!phaser.isTerminated());
                }
            }.start();
        });
        // allow threads to start and deregister self
        System.out.println("Ok i'm done... boys u can continue");
        phaser.arriveAndDeregister();
    }
}
