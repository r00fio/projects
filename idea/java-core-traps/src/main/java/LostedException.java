/**
 * Created by pixel on 4/3/15.
 */
public class LostedException {

    public static void main(String[] args) {
        doSomething();
    }

    public static void doSomething() {
        try {
            //Normally you would have code that doesn't explicitly appear
            //to throw exceptions so it would be harder to see the problem.
            throw new RuntimeException();
        } finally {
            return;
        }
    }
}
