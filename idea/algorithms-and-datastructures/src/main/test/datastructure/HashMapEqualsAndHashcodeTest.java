package datastructure;

/**
 * Created by pixel on 4/25/15.
 * Q : Why default need hashCode?
 * A : for hash data structures. Like HashMap.
 *
 * Q : Why override hashCode when override equals?
 * A :
 *      if hc1 = hc2 not means that o1.equals(o2)
 *      if hc1 != hc2 not mean that o1. not equals(o2)
 *      buuut, if o1.equals(o2) the hc1 must be = hc2,
 *      that's why need to override hc. 2 rule {@link Object#hashCode()}
 *      Why? it can hit to different buckets in HashTable
 *
 * Generally equals and hash code not linked with each other.
 * This means that exists cases when i don't need hashCode, only equals and v.versa
 */
public class HashMapEqualsAndHashcodeTest {

    public void testPutGet(){

    }

    class Summary{
        private String name;
        private String content;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Summary summary = (Summary) o;

            if (content != null ? !content.equals(summary.content) : summary.content != null) return false;
            if (name != null ? !name.equals(summary.name) : summary.name != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (content != null ? content.hashCode() : 0);
            return result;
        }
    }
}
