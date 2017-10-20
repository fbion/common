package test.performancetest.concurrency.test1;

import java.util.concurrent.atomic.AtomicLong;

/**
 * description: <br>
 * createTime: 2017/10/2014:45 <br>
 *
 * @author zzh
 */
public class AtomicCounter implements Counter {
    AtomicLong counter = new AtomicLong(0);

    @Override
    public void increment() {
        counter.incrementAndGet();
    }

    @Override
    public long getCounter() {
        return counter.get();
    }


    public static void main(String[] args) throws InterruptedException {
        TestDriver.test(new AtomicCounter());
    }
}
/***
 Counter result: 100000000
 Time passed in ms:2418
 ***/
