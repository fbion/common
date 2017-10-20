package advance.JVM.oom;

import sun.misc.Unsafe;
import test.utils.GetUnsafe;

/**
 * Created by Administrator on 2016/5/31.
 * VM Args:-Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Unsafe unsafe = GetUnsafe.getUnsafe();
        while(true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
