package traps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 8lackC on 6/10/15.
 */
public class ArrayListCorrectRemoveElement {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<Integer>() {
            {
                for (int i = 0; i < 50; i++) {
                    if (i % 2 == 0) add(i);
                }
            }
        };
//        l.remove(12);//not correct way, because it will shift all collection
        l.set(12, l.get(l.size() - 1));
        l.remove(l.size() - 1);
    }
}
