/**
 * Created by 8lackC on 5/14/15.
 */
public class ReentrancyDemo {
    static class Widget{
        protected synchronized void doSome(){
            System.out.println("DOne");
        }
    }
    static class Child extends Widget{
        private synchronized void doSomeV2(){
            System.out.println("DOne");
            super.doSome();
        }
    }
    public static synchronized void main(String[] args) {
        new Child().doSomeV2();
    }

}
