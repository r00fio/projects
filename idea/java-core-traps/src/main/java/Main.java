/**
 * Created by 8lackC on 6/11/15.
 */
public class Main {

    public static void main(String[] args) {
        ThreadLocal threadLocal = ThreadLocal.withInitial(() -> 11);
        ThreadLocal threadLocal2 = ThreadLocal.withInitial(() -> 11);
        Thread res;
        for (int i = 0; i < 10; i++) {
            Integer value = new Integer(i);

            Thread t = new Thread(() -> {
                while (true) {

                    threadLocal.set(value);
                    threadLocal2.set(value);
                    Integer o = (Integer)threadLocal.get();
                    Integer o1 = (Integer)threadLocal2.get();
                    if (value != 5) {
                        Thread.currentThread().interrupt();
                    }
//                    System.out.println(o);
                    System.out.println(o1);

                }
            });
            t.start();
            if (i == 5) {
                res = t;
            }
        }


    }

}
