package Algorithms.test;

/**
 * Created by Administrator on 2016/9/10.
 */
public class Test {
    static final int MAXIMUM_CAPACITY = (1 << 30) - 1;

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        for (int i = 1 ; i < 16; i++) {
            System.out.print(i + "\t\t");
            System.out.println(tableSizeFor(i));
        }
        System.out.println("MAXIMUM_CAPACITY = " + MAXIMUM_CAPACITY);
        System.out.print((1<<28) + "\t\t");
        System.out.println(tableSizeFor(1<<28));
        System.out.print((1<<29) + "\t\t");
        System.out.println(tableSizeFor(1<<29));
        System.out.print((1<<31) + "\t\t");
        System.out.println(tableSizeFor(1<<31));
//        int n = 10;
//        System.out.println(n >>> 1);
//        System.out.println(5 | 10);
//        System.out.println(n |= n >>> 1);
    }
}

