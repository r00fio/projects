package algorithm;

import java.lang.reflect.Array;

/**
 * Created by pixel
 */
public class MergeSort {


    public static <E extends Comparable> E[] sort(E[] elements) throws IllegalAccessException, InstantiationException {
        E[] o = (E[]) Array.newInstance(elements.getClass().getComponentType(), elements.length);
        sort(elements, o, 0, elements.length);
        return elements;
    }

    private static <E extends Comparable> void sort(E[] elements, E[] tmp, int left, int right) {
        int mid = (right + left) / 2;
        if (left < mid) {
            sort(elements, tmp, left, mid);
            sort(elements, tmp, mid + 1, right);
        }
        merge(elements, tmp, left, mid <= left ? right : mid, right);
    }

    private static <E extends Comparable> void merge(E[] elements, E[] tmp, int leftCursor, int rightCursor, int max) {
        int hi = rightCursor;
        int lo = leftCursor;
        int start = leftCursor;
        while (leftCursor != hi && rightCursor <= max) {
            E l = elements[leftCursor], r = elements[rightCursor];

            if (l.compareTo(r) == 1) {
                tmp[start++] = r;
                rightCursor++;
            } else {
                tmp[leftCursor++] = l;
            }
        }
        copyResult(elements, tmp, lo, start);
    }

    private static <E extends Comparable> void copyResult(E[] elements, E[] tmp, int min, int start) {
        while (start > min) {
            start--;
            elements[start] = tmp[start];
            tmp[start] = null;
        }
    }
}