package test.utils;

import sun.misc.Unsafe;

/**
 * description： <br>
 * createTime: 2017/10/2011:06 <br>
 *
 * @author zzh
 */
public class Addresser {

    private static Unsafe unsafe = GetUnsafe.getUnsafe();

    public static long addressOf(Object o) throws Exception {
        Object[] array = new Object[]{o};

        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        int addressSize = unsafe.addressSize();
        long objectAddress;
        switch (addressSize) {
            case 4:
                objectAddress = unsafe.getInt(array, baseOffset);
                break;
            case 8:
                objectAddress = unsafe.getLong(array, baseOffset);
                break;
            default:
                throw new Error("unsupported address size: " + addressSize);
        }

        return (objectAddress);
    }


    public static void main(String... args)
            throws Exception {
        Object mine = "Hi there".toCharArray();
        long address = addressOf(mine);
        System.out.println("Addess: " + address);

        //Verify address works - should see the characters in the array in the output
        printBytes(address, 27);

        long intClassAddress = normalize(GetUnsafe.getUnsafe().getInt(new Integer(0), 4L));
        System.out.println(intClassAddress);
        long strClassAddress = normalize(GetUnsafe.getUnsafe().getInt("", 4L));
        System.out.println(strClassAddress);
        GetUnsafe.getUnsafe().putAddress(intClassAddress + 36, strClassAddress);
        System.out.println(3);
    }


    public static void printBytes(long objectAddress, int num) {
        for (long i = 0; i < num; i++) {
            int cur = unsafe.getByte(objectAddress + i);
            System.out.print((char) cur);
        }
        System.out.println();
    }

    private static long normalize(int value) {
        if (value >= 0) {
            return value;
        }
        return (~0L >>> 32) & value;
    }
}
