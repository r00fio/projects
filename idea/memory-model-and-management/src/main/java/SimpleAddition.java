/**
 * Created by bebe on 2/6/15.
 */
public class SimpleAddition {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            abracadabra();
        }
    }
    public static long abracadabra(){
        long l = 0x7ffffffffffffffaL;
        l += 2;
        return l ;
    }
}
