/**
 * Created by 8lackC on 5/6/15.
 */
public class ConcurrentRead {
    private int incMe1;
    private volatile int calls; //

    public static void main(String[] args) {
        ConcurrentRead concurrentRead = new ConcurrentRead();
        new Thread(concurrentRead::increment).start();
        new Thread(concurrentRead::show).start();
    }

    public void increment() {
        while (incMe1 < Integer.MAX_VALUE) {
            calls++;
            incMe1++; // happens before volatile var not works
        }
    }

    public void show() {
        while (incMe1 < Integer.MAX_VALUE) {
            if (calls != incMe1) {
                System.out.println(calls + "!=" + incMe1);
            }
        }
    }

}