import java.util.ArrayList;
import java.util.Spliterators;

/**
 *
 */
public class Hello {
    public void show(){
        System.out.println(1);
    }

   public static class A extends Hello{

       public void show(){
           System.out.println(10);
       }

       public static void main(String[] args) {
           A a = new A();
           Hello h = (Hello) a;
           h.show();
       }

    }
}