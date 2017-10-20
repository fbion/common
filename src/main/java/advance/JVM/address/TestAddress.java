package advance.JVM.address;

import sun.misc.Unsafe;
import test.utils.Addresser;
import test.utils.GetUnsafe;

import java.lang.reflect.Field;

/**
 * description: <br>
 * createTime: 2017/10/209:49 <br>
 *
 * @author zzh
 */
public class TestAddress {
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = GetUnsafe.getUnsafe();
        MyLong m = new MyLong(1000L);
        Field f = Long.class.getDeclaredField("value");
        long offset = unsafe.objectFieldOffset(f);
        System.out.println(m);
        long address = Addresser.addressOf(m);
        System.out.println("address : " + address);
        unsafe.putAddress(address + offset, 2000);
        System.out.println(m);
//        Long l = 1000L;
//        Field f = Long.class.getDeclaredField("value");
//        long offset = unsafe.fieldOffset(f);
//        System.out.println(l);
//        long address = Addresser.addressOf(l);
//        System.out.println("address : " + address);
//        unsafe.putAddress(address + offset, 2);
//        System.out.println(l);
    }
}
class MyLong{
    private long value;
    public MyLong(long value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value + "";
    }
}
