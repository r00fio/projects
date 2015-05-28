/**
 * Created by 8lackC on 5/6/15.
 */
public class VolatileExmpV2 {

    private int incMe1;
    private int incMe2;

    public static void main(String[] args) {
        VolatileExmpV2 volatileExmpV2 = new VolatileExmpV2();
        new Thread(volatileExmpV2::increment).start();
        new Thread(volatileExmpV2::show).start();
    }

    public void increment() {
        while (incMe1 < Integer.MAX_VALUE || incMe2 < Integer.MAX_VALUE){
            incMe1++; incMe2++;
        }

    }

    public synchronized void show() {
        while (incMe1 < Integer.MAX_VALUE || incMe2 < Integer.MAX_VALUE) {
            if (incMe2 > incMe1) {// why CPU do this? When incMe2 is hot CPU can compute it
                System.out.println(incMe1 + " = " + incMe2);
            }
        }
    }
}
