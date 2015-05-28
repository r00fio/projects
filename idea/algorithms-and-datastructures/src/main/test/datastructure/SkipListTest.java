package datastructure;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SkipListTest {
    @DataProvider(name = "myTest")
    public Object[][] createData() {
        return new Object[][]{
                {43},
                {12},
                {22},
                {52},
                {12},
                {3},
                {42},
                {5},
                {6},
                {7},
                {133},
                {132},
                {142},
                {1643},
                {13},
                {152},
                {621},
                {21},
                {61},
        };
    }
    SkipList<Integer> integerSkipList = new SkipList<>();
    @Test(dataProvider = "myTest")
    public void testAdd(Integer i) throws Exception {
        integerSkipList.add(i);
        assertEquals(i,integerSkipList.get(i));

    }
}