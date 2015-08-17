package concurrent.util;

import java.util.Objects;

/**
 * Created by air on 05/08/15.
 */
public class HowWaitWorks {
    private static final Object lock = new Object();
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                synchronized (lock) {
                    try {
                        System.out.println("Releasing lock");
                        lock.wait();// All this threads are inserted to the
                        // waitSet of lock
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Wating thread runs " + Thread.currentThread().getName());
                }
            });
            t.start();
        }
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("Not Waiting thread runs");
                lock.notifyAll();
            }
        }).start();
    }
}
