package datastructure;

import java.util.Objects;

/**
 * Created by pixel on 4/23/15.
 */
public class RWTrie<V> implements Trie<V> {
    private static final byte R = 127; // Radix -alphabet size

    private static class Node {
        private Object value;
        private Node[] next = new Node[R]; // why generic array creation when remove static from Node
    }

    private Node tail = new Node();

    @Override
    public V get(String key) {
        if (key.isEmpty()) {
            return null;
        }
        Node n = tail;
        int length = 0;
        for (char c : key.toCharArray()) {
            if (n.next != null) {
                n = n.next[c];
            }
            length++;
        }
        return length == key.length() ? (V) n.value : null;
    }

    @Override
    public V put(String key, V val) {
        if (key.isEmpty()) {
            return null;
        }
        Node n = tail;
        for (char c : key.toCharArray()) {
            if (n.next[c] == null) {
                n.next[c] = new Node();
            }
            n = n.next[c];
        }
        V v = (V) n.value;
        n.value = val;
        return v;
    }

    @Override
    public V delete(String key) {
        if (key.isEmpty()) {
            return null;
        }
        Node endOfKey = tail;
        Node prevValued = tail;
        int length = 0;
        for (char c : key.toCharArray()) {
            length++;
            if (endOfKey.value != null) {
                prevValued = endOfKey;
            }
            if (endOfKey.next != null && endOfKey.next[c] != null) {
                if (length == key.length()) {
                    endOfKey.next[c].value = null;
                    boolean hasElements = false;
                    for (Node n : endOfKey.next) {
                        if (n != null) hasElements = true;
                    }
                    if (!hasElements){
                        endOfKey.next[c] = null;
                    }
                }
                endOfKey = endOfKey.next[c];
            }
        }

        return null;
    }
}
