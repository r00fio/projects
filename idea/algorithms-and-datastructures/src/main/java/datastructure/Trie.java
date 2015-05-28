package datastructure;

/**
 * Created by pixel on 4/23/15.
 */
public interface Trie<V> {
    /**
     *
     * @param key
     * @return
     */
    public V get(String key);

    /**
     *
     * @param key
     * @param val
     * @return old value if exists or null if not
     */
    public V put(String key, V val);

    /**
     *
     * @param key
     * @return deleted value if exists or null if not
     */
    public V delete(String key);
}
