package algorithm;

/**
 * Created by pixel on 4/8/15.
 */
public class QuickSort {
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int end = partition(a, lo, hi); // end is a point where lo > hi
        sort(a, lo, end - 1);
        sort(a, end + 1, hi);
    }

    static int partition(Comparable[] a, int lo, int hi) { // Partition into a[lo..start-1], a[start], a[start+1..hi].
        int start = lo, end = hi + 1;
        // left and right scan indices
        Comparable pivot = a[lo];
        // partitioning item
        while (true) { // Scan right, scan left, check for scan complete, and exchange.
            while (less(a[++start], pivot)) {
                if (start == hi) break;
            }
            while (less(pivot, a[--end])) {
                if (end == lo) break;
            }
            if (start >= end) break;
            exch(a, start, end);
        }
        exch(a, lo, end);
        // Put pivot = a[end] into position
        return end;
        // with a[lo..end-1] <= a[end] <= a[end+1..hi].
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static boolean less(Comparable left, Comparable right) {
        return left.compareTo(right) == -1;
    }

}
