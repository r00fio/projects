/**
 * Created by bebe on 1/28/15.
 */
public class ConstructionNotAtomic {
    private static int state;
    public ConstructionNotAtomic(){
        try {
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        state = 33;
    }

    public static void main(String[] args) {
        new  Thread(ConstructionNotAtomic::new).start();
        System.out.println(state);
    }
}
