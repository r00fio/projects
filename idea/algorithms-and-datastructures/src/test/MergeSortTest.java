import static org.junit.Assert.*;

public class MergeSortTest {

    @org.junit.Test
    public void testSort() throws Exception {
        Integer elements[] = {43,42,340,4,2,243,566,2,6,34,1,3,54,560,26,41,8,3};
        Integer expectedElements[] = {1,2,2,3,3,4,6,8,26,34,41,43,42,54,243,340,560,566};
        assertArrayEquals(expectedElements, MergeSort.sort(elements));
    }
}