/**
 * Created by bebe on 1/28/15.
 */
public class WaitExample {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    synchronized (Thread.currentThread()) {
                        Thread.currentThread().wait(); // What happens with CPU when 1000th are became in WAITING state
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
