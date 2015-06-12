package traps;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 8lackC on 6/10/15.
 */
public class LostedElementInHashMap {
    private static Map<Key, String> map = new HashMap() {
        {
            for (int i = 0; i < 40; i++) {
                put(new Key(i, new String(i + "")), Integer.toString(i));
            }
        }
    };

    public static void main(String[] args) {
        map.keySet().forEach(k -> {
            if (k.equals(new Key(4, 4 + ""))) {
                k.id = 99;
            }
        });
        String s = map.get(new Key(4, 4 + ""));
        assert s == null;
    }

    static class Key {
        int id;
        String name;

        Key(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Key)) return false;

            Key key = (Key) o;

            if (id != key.id) return false;
            if (name != null ? !name.equals(key.name) : key.name != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = id;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            return result;
        }
    }
}

