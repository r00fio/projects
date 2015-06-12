import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 8lackC on 6/8/15.
 */
public class OverrideRules {
    public static void main(String[] args) {

    }

    class M {
        protected Collection method(Collection list) {
            return null;
        }
    }

    class M2 extends M{
        @Override
        public Collection method(Collection list) { // you can give access upper then in parent, but cant give a lover like package private
            return null;
        }
    }

    class M3 extends M {
        @Override
        protected List method(Collection list) { // you can set child of  return type but not parent, bacause open for
                                                // extension, u can't modify parameter type
            return null;
        }
    }
}
