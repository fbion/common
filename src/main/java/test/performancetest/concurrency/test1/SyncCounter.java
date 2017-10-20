package test.performancetest.concurrency.test1;

/**
 * description: <br>
 * createTime: 2017/10/2014:45 <br>
 *
 * @author zzh
 */
public class SyncCounter implements Counter {
    private volatile long counter = 0;

    @Override
    public synchronized void increment() {
        counter++;
    }

    @Override
    public long getCounter() {
        return counter;
    }


    public static void main(String[] args) throws InterruptedException {
        TestDriver.test(new SyncCounter());
    }
}
/***
 Counter result: 100000000
 Time passed in ms:4521
 ***/