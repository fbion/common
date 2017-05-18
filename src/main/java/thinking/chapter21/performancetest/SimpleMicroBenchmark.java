package thinking.chapter21.performancetest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/4/27.
 */
public class SimpleMicroBenchmark {
    static long test(Incrementable incrementable) {
        long start = System.nanoTime();
        for (int i = 0; i < 10000000L; i++) {
            incrementable.incretment();
        }
        return System.nanoTime() - start;
    }

    public static void main(String[] args) {
        long synchTime = test(new SynchronizingTest());
        long lockTime = test(new LockingTest());
        System.out.printf("synchronized:%1$10d\n", synchTime);
        System.out.printf("Lock:%1$10d\n", lockTime);
        System.out.printf("Lock/synchronized:%1$.3f\n", (double)lockTime/(double)synchTime);
    }
}

abstract class Incrementable {
    protected long counter = 0;
    public abstract void incretment();
}

class SynchronizingTest extends Incrementable {
    @Override
    public void incretment() {
        ++counter;
    }
}

class LockingTest extends Incrementable {
    private Lock lock = new ReentrantLock();

    @Override
    public void incretment() {
        lock.lock();
        try {
            ++counter;
        } finally {
            lock.unlock();
        }
    }
}