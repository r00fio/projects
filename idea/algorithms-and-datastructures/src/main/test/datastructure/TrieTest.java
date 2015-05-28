package datastructure;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TrieTest {

    @DataProvider(name = "myTest")
    public Object[][] createData() {
        return new Object[][]{
                {"India", 1},
                {"Brazil", 2},
                {"Canada", 3},
                {"Sri Lanka", 4},
                {"England", 5},
                {"UK", 6},
                {"United States", 7},
        };
    }

    @Test(dataProvider = "myTest")
    public void testGet(String key, Integer val) throws Exception {
        Trie<Integer> trie = new RWTrie();
        trie.put(key, val);
        assertEquals(trie.get(key), val);
    }

    @Test(dataProvider = "myTest")
    public void testDelete(String key, Integer val) throws Exception {
        Trie<Integer> trie = new RWTrie();
        trie.put(key, val);
        trie.delete(key);
        assertEquals(trie.get(key), null);
    }
}