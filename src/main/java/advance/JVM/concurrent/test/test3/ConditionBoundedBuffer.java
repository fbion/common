package advance.JVM.concurrent.test.test3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description： <br>
 * createTime: 2018/3/148:38 <br>
 *
 * @author zzh
 */
@ThreadSafe
public class ConditionBoundedBuffer<T> {

    private static final int BUFFER_SIZE = 100;

    protected final Lock lock = new ReentrantLock();

    private final Condition NotFul = lock.newCondition();

    private final Condition NotEmpty = lock.newCondition();

    @GuardedBy("this")
    private final T[] items;

    @GuardedBy("this")
    private int tail;

    @GuardedBy("this")
    private int head;

    @GuardedBy("this")
    private int count;

    protected ConditionBoundedBuffer(int capacity) {
        this.items = (T[]) new Object[capacity];
    }

    public void put(T t) throws InterruptedException {
        lock.lock();
        try {
           while (count == items.length) {
               NotFul.await();
           }
           items[tail] = t;
           if(++tail == items.length) {
               tail = 0;
           }
           ++count;
            //满足使用signal而不使用signalAll的两个条件：
            // 单进单出，
            // 所有等待该条件谓词的线程类型相同，因为整个类实现里只有take方法一处等待该谓词，所以所有等待的线程类型相同
           NotEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while(count == 0) {
                NotEmpty.await();
            }
            T t = items[head];
            items[head] = null;
            if(++head == items.length) {
                head = 0;
            }
            --count;
            //满足使用signal而不使用signalAll的两个条件：
            // 单进单出，
            // 所有等待该条件谓词的线程类型相同，因为整个类实现里只有put方法一处等待该谓词，所以所有等待的线程类型相同
            NotFul.signal();
            return t;
        } finally {
            lock.unlock();
        }
    }
}
