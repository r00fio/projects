/**
 * Created by pixel on 4/15/15.
 */
public class StaticMethods {
    public static void main(String[] args) {
        M1 m2 = new M2();
        m2.show();
    }
}
class M2 extends M1{
    public static void show(){
        System.out.println("M2");
    }
}

class M1{
    public static void show(){
        System.out.println("M1");
    }
}
