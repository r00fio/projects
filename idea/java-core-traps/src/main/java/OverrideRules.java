import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 8lackC on 6/8/15.
 */
public class OverrideRules {
    public static void main(String[] args) {

    }

    interface I {
        Iterable method(Iterable iterable);
    }

    class M implements I{

        @Override
        public Collection method(Iterable iterable) {
            return null;
        }
    }

    class M2 extends M{
        @Override
        public Collection method(Iterable list) { // you can give access upper then in parent, but cant give a lover like package private
            return null;
        }
    }

    class M3 extends M {
        @Override
        public List method(Iterable list) { // you can set child of  return type but not parent, bacause open for
            return null;
        }
    }
}
