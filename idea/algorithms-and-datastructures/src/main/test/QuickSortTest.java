import algorithm.QuickSort;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {

    @Test
    public void testSort() throws Exception {
        RandomNumbersHolder randomNumbersHolder = new RandomNumbersHolder();
        QuickSort.sort(randomNumbersHolder.getUnsorted());
        assertArrayEquals(randomNumbersHolder.getSorted(),randomNumbersHolder.getUnsorted());
    }
}