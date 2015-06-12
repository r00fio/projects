package concur;

import java.util.concurrent.locks.StampedLock;

/**
 * Created by 8lackC on 6/10/15.
 */
public class WaitAndSleep {
    private static StampedLock lock = new StampedLock();
    private static Object l = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("new Thread trying acquire the lock");
            try {
                synchronized (l) {// Before wait() we must became owner?
                    // because if
                    System.out.println("new Thread trying acquired lock");
                    Thread.currentThread().join();
                    l.notify(); // 1
                    l.wait();// read doc its good. Releases monitor
                    System.out.println("new Thread awaken");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("Main thread trying acquire the lock");
        try {
            synchronized (l) {
                System.out.println("Main thread acquired lock");
                l.notify(); // 2
                l.wait();
                System.out.println("Main thread awaiken");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main thread ");
    }
}
