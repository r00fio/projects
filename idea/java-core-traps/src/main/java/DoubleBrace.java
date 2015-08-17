import java.util.ArrayList;
import java.util.List;

/**
 * Created by pixel on 4/3/15.
 */
public class DoubleBrace {
    static List<String> strings = new ArrayList(){{
        add("1");
        add("2");
    }};

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList() {
            @Override
            public boolean add(Object o) {
                System.out.println(o);
                return super.add(o);
            }

            {
                add("1");
                add("2");
                add("3");
                add(3);
            }
        };
        System.out.println(strings);
    }
}