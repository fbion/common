package test.performancetest.concurrency.test1;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * description: <br>
 * createTime: 2017/10/2014:45 <br>
 *
 * @author zzh
 */
public class LockCounter implements Counter {
    private volatile long counter = 0;
    private ReentrantReadWriteLock.WriteLock lock = new ReentrantReadWriteLock().writeLock();

    @Override
    public void increment() {
        lock.lock();
        counter++;
        lock.unlock();
    }

    @Override
    public long getCounter() {
        return counter;
    }


    public static void main(String[] args) throws InterruptedException {
        TestDriver.test(new LockCounter());
    }
}
/***
 Counter result: 100000000
 Time passed in ms:4186
 ***/
