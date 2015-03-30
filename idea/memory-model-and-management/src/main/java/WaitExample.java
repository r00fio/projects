/**
 * Created by bebe on 1/28/15.
 *
 * http://www.logicaltrinkets.com/wordpress/?p=153
 * http://www-01.ibm.com/support/knowledgecenter/SSYKE2_7.0.0/com.ibm.java.win.70.doc/diag/tools/javadump_tags_blocked_threads.html
 * synchronized(LOCK) {
 Thread.sleep(1000); // LOCK is held
 }


 synchronized(LOCK) {
 LOCK.wait(); // LOCK is not held
 }
 It all eventually makes its way down to the OS’s scheduler, which hands out timeslices to processes and threads.

 sleep(n) says “I’m done with my timeslice, and please don’t give me another one for at least n milliseconds.” The OS doesn’t even try to schedule the sleeping thread until requested time has passed.

 yield() says “I’m done with my timeslice, but I still have work to do.” The OS is free to immediately give the thread another timeslice, or to give some other thread or process the CPU the yielding thread just gave up.

 .wait() says “I’m done with my timeslice. Don’t give me another timeslice until someone calls notify().” As with sleep(), the OS won’t even try to schedule your task unless someone calls notify() (or one of a few other wakeup scenarios occurs).

 Threads also lose the remainder of their timeslice when they perform blocking IO and under a few other circumstances. If a thread works through the entire timeslice, the OS forcibly takes control roughly as if yield() had been called, so that other processes can run.

 You rarely need yield(), but if you have a compute-heavy app with logical task boundaries, inserting a yield() might improve system responsiveness (at the expense of time — context switches, even just to the OS and back, aren’t free). Measure and test against goals you care about, as always.
 */
public class WaitExample {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    Object lock = new Object();
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
