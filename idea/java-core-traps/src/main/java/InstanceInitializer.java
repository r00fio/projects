/**
 * Created by pixel on 4/3/15.
 */
public class InstanceInitializer {

    {
        System.out.println("instance initialiser called");
    }

    static {
        System.out.println("static initialiser called");
    }

    public InstanceInitializer(){
        System.out.println("constructor called");
    }

    public static void main(String[] args) {
        new InstanceInitializer();
    }
}
