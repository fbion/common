package test.utils;

import sun.misc.Unsafe;

/**
 * description: <br>
 * createTime: 2017/10/2014:28 <br>
 *
 * @author zzh
 */
public class UnsafeUtils {

    private static Unsafe unsafe = GetUnsafe.getUnsafe();


    public static long sizeOf(Object object) {
        return unsafe.getAddress(normalize(unsafe.getInt(object, 4L)) + 12L);

    }


    private static long normalize(int value) {
        if (value >= 0) {
            return value;
        }
        return (~0L >>> 32) & value;
    }


    /**
     * 网上copy,运行有错
     */
    public static void test() {
        long intClassAddress = normalize(GetUnsafe.getUnsafe().getInt(new Integer(0), 4L));
        long strClassAddress = normalize(GetUnsafe.getUnsafe().getInt("", 4L));
        GetUnsafe.getUnsafe().putAddress(intClassAddress + 36, strClassAddress);
    }

    public static void main(String[] args) {

    }
}
