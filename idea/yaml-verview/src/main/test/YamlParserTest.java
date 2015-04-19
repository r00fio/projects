import org.junit.Test;

import static org.junit.Assert.*;

public class YamlParserTest {

//    @Test
//    public void testParse() throws Exception {
//        String data = "- 1\n" +
//                "- apple\n" +
//                "- orange";
//        Sequence expected = new Sequence("", "");
//        Sequence sequence = YamlParser.<Sequence>parse(data);
//        assertEquals(expected, sequence);
//
//    }
    @Test
    public void testParse() throws Exception {
        String data = "name: John Smith\n" +
                "age: 37\n" +
                "spouse:\n" +
                "    name: Jane Smith\n" +
                "    age: 25\n" +
                "children:\n" +
                "    -   name: Jimmy Smith\n" +
                "        age: 15\n" +
                "    -   name: Jenny Smith\n" +
                "        age 12";
        Sequence expected = new Sequence("", "");
        Sequence sequence = YamlParser.<Sequence>parse(data);
        assertEquals(expected, sequence);
    }
}