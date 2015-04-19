import java.util.ArrayList;
import java.util.List;

/**
 * Created by pixel on 4/3/15.
 */
public class LiteralThings {
    private static List<String> list = new ArrayList() {{
        add("1");
        add("2");
    }};

    public static void main(String[] args) throws InterruptedException {
        for (; ; ){
            Thread.currentThread().sleep(1000);
            System.out.println(list);
        }
    }
}
