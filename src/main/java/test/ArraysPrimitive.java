package test;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：ArraysPrimitive</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/29 16:05<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/29 16:05<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class ArraysPrimitive {
    private ArraysPrimitive() {}

    /**
     * 对指定的 int 型数组按数字升序进行排序。
     */
    public static void sort(int[] a) {
        sort1(a, 0, a.length);
    }

    /**
     * 对指定 int 型数组的指定范围按数字升序进行排序。
     */
    public static void sort(int[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        sort1(a, fromIndex, toIndex - fromIndex);
    }

    private static void sort1(int x[], int off, int len) {
        /*
         * 当待排序的数组中的元素个数小于 7 时，采用插入排序 。
         *
         * 尽管插入排序的时间复杂度为O(n^2),但是当数组元素较少时， 插入排序优于快速排序，因为这时快速排序的递归操作影响性能。
         */
        if (len < 7) {
            for (int i = off; i < len + off; i++)
                for (int j = i; j > off && x[j - 1] > x[j]; j--)
                    swap(x, j, j - 1);
            return;
        }
        /*
         * 当待排序的数组中的元素个数大于 或等于7 时，采用快速排序 。
         *
         * Choose a partition element, v
         * 选取一个划分元，V
         *
         * 较好的选择了划分元(基准元素)。能够将数组分成大致两个相等的部分，避免出现最坏的情况。例如当数组有序的的情况下，
         * 选择第一个元素作为划分元，将使得算法的时间复杂度达到O(n^2).
         */
        // 当数组大小为size=7时 ，取数组中间元素作为划分元。
        int m = off + (len >> 1);
        // 当数组大小 7<size<=40时，取首、中、末 三个元素中间大小的元素作为划分元。
        if (len > 7) {
            int l = off;
            int n = off + len - 1;
            /*
             * 当数组大小  size>40 时 ，从待排数组中较均匀的选择9个元素，
             * 选出一个伪中数做为划分元。
             */
            if (len > 40) {
                int s = len / 8;
                l = med3(x, l, l + s, l + 2 * s);
                m = med3(x, m - s, m, m + s);
                n = med3(x, n - 2 * s, n - s, n);
            }
            // 取出中间大小的元素的位置。
            m = med3(x, l, m, n); // Mid-size, med of 3
        }

        //得到划分元V
        int v = x[m];

        // Establish Invariant: v* (<v)* (>v)* v*
        int a = off, b = a, c = off + len - 1, d = c;
        while (true) {
            while (b <= c && x[b] <= v) {
                if (x[b] == v)
                    swap(x, a++, b);
                b++;
            }
            while (c >= b && x[c] >= v) {
                if (x[c] == v)
                    swap(x, c, d--);
                c--;
            }
            if (b > c)
                break;
            swap(x, b++, c--);
        }
        // Swap partition elements back to middle
        int s, n = off + len;
        s = Math.min(a - off, b - a);
        vecswap(x, off, b - s, s);
        s = Math.min(d - c, n - d - 1);
        vecswap(x, b, n - s, s);
        // Recursively sort non-partition-elements
        if ((s = b - a) > 1)
            sort1(x, off, s);
        if ((s = d - c) > 1)
            sort1(x, n - s, s);
    }

    /**
     * Swaps x[a] with x[b].
     */
    private static void swap(int x[], int a, int b) {
        int t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * Swaps x[a .. (a+n-1)] with x[b .. (b+n-1)].
     */
    private static void vecswap(int x[], int a, int b, int n) {
        for (int i=0; i<n; i++, a++, b++)
            swap(x, a, b);
    }

    /**
     * Returns the index of the median of the three indexed integers.
     */
    private static int med3(int x[], int a, int b, int c) {
        return (x[a] < x[b] ? (x[b] < x[c] ? b : x[a] < x[c] ? c : a)
                : (x[b] > x[c] ? b : x[a] > x[c] ? c : a));
    }

    /**
     * Check that fromIndex and toIndex are in range, and throw an
     * appropriate exception if they aren't.
     */
    private static void rangeCheck(int arrayLen, int fromIndex, int toIndex) {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex
                    + ") > toIndex(" + toIndex + ")");
        if (fromIndex < 0)
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        if (toIndex > arrayLen)
            throw new ArrayIndexOutOfBoundsException(toIndex);
    }
}