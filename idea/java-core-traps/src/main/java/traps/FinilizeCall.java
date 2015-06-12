package traps;

/**
 * Created by 8lackC on 6/8/15.
 * In Effective java (2nd edition ) Joshua bloch says,

 "Oh, and one more thing: there is a severe performance penalty for using finalizers. On my machine, the time
 to create and destroy a simple object is about 5.6 ns.
 Adding a finalizer increases the time to 2,400 ns. In other words, it is about 430 times slower to create and
 destroy objects with finalizers."
 */

/**
 * finalize() execution is not guaranteed at all (with example)
 */
public class FinilizeCall {
    public FinilizeCall(){
        System.out.println("Called");
        M m = new M();
        System.out.println(m);

    }
    static boolean a = false;
    public static void main(String[] args) {
        new FinilizeCall();

        new Thread(() -> {
            while (true) {
                if (a) {
                    break;
                }
            }
        }).start();
    }

    static class M {
        @Override
        protected void finalize() throws Throwable {
            a = true;
            System.out.println("Destroing M");
            super.finalize();
        }
    }
}
