import java.util.Scanner;

/**
 * Created by pixel on 4/20/15.
 */
public class Test {

    static int solveMeFirst(int a, int b) {
        return a + b;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int _a;
        _a = in.nextInt();
        int _b;
        _b = in.nextInt();
        int sum;
        sum = solveMeFirst(_a, _b);
        System.out.println(sum);
    }
}
