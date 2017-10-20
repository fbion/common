package test.performancetest.concurrency.test1;

import sun.misc.Unsafe;
import test.utils.GetUnsafe;

/**
 * description: <br>
 * createTime: 2017/10/2014:45 <br>
 *
 * @author zzh
 */
public class CASCounter implements Counter {
    private volatile long counter = 0;
    private Unsafe unsafe;
    private long offset;

    public CASCounter() throws Exception {
        unsafe = GetUnsafe.getUnsafe();
        offset = unsafe.objectFieldOffset(CASCounter.class.getDeclaredField("counter"));
    }

    @Override
    public void increment() {
        long before = counter;
        while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
            before = counter;
        }
    }

    @Override
    public long getCounter() {
        return counter;
    }


    public static void main(String[] args) throws Exception {
        TestDriver.test(new CASCounter());
    }
}
/***
 Counter result: 100000000
 Time passed in ms:7991
 ***/

