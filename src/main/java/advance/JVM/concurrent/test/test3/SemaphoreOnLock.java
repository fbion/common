package advance.JVM.concurrent.test.test3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description：用Lock实现的信号量 <br>
 * createTime: 2018/3/158:34 <br>
 *
 * @author zzh
 */
@ThreadSafe
public class SemaphoreOnLock {

    private final Lock lock = new ReentrantLock();

    private final Condition permitsAvailable = lock.newCondition();

    @GuardedBy("lock")
    private int permits;


    public SemaphoreOnLock(int permits) {
        lock.lock();
        try {
            this.permits = permits;
        } finally {
            lock.unlock();
        }
    }


    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits <= 0) {
                permitsAvailable.await();
            }
            --permits;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            ++permits;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }
}
