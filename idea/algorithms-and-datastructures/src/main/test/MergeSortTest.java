import algorithm.MergeSort;

import static org.junit.Assert.assertArrayEquals;

public class MergeSortTest {



    @org.junit.Test
    public void testSort() throws Exception {
        RandomNumbersHolder randomNumbersHolder = new RandomNumbersHolder();
        MergeSort.sort(randomNumbersHolder.getUnsorted());
        assertArrayEquals(randomNumbersHolder.getSorted(), randomNumbersHolder.getUnsorted());
    }
}