package thinking.chapter21.shareresources;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/3/29.
 */
public class MutexEventGenerator extends IntGenerator{
    private int currentEventValue = 0;
    private Lock lock = new ReentrantLock();
    @Override
    public int next() {
        lock.lock();
        try{
            ++currentEventValue;
            Thread.yield();
            ++currentEventValue;
            return currentEventValue;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEventGenerator());
    }
}
