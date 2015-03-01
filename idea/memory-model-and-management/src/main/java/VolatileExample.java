import java.io.*;

/**
 * Created by bebe on 1/16/15.
 */
public class VolatileExample {
    private static volatile int incMe1 = 0;
    private static volatile int incMe2 = 0;
    private long aLong1 = 0;
    private long aLong2 = 0;
    private long aLong3 = 0;
    private long aLong4 = 0;
    private long aLong5 = 0;
    private long aLong6 = 0;
    private long aLong7 = 0;
    private long aLong8 = 0;
    private static final int TRIES = 20;
    private static final int MAX_INCREMENT = 10000000;

    public static void main(String[] args) throws IOException {
        new Thread(()->{
            long start = System.nanoTime();
            int tries = 0;
            long end;
            while (true){
                incMe1++;
                end = System.nanoTime() - start;
                if (incMe1 % MAX_INCREMENT == 0){
                    System.out.println("A: " + end/MAX_INCREMENT*10 + " ms");
                    incMe1 = 0;
                    if (++tries > TRIES){
                        Runtime.getRuntime().exit(0);
                    }
                    start = System.nanoTime();
                }
            }
        }).start();

        new Thread(()->{
            long start = System.nanoTime();
            int tries = 0;
            long end;
            while (true){
                incMe2++;
                end = System.nanoTime() - start;
                if (incMe2 % MAX_INCREMENT == 0){
                    System.out.println("B: " + end/MAX_INCREMENT*10  + " ms");
                    incMe2 = 0;
                    if (++tries > TRIES){
                        Runtime.getRuntime().exit(0);
                    }
                    start = System.nanoTime();
                }
            }
        }).start();
    }
}
