package Algorithms.puzzle.frame;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.CountDownLatch;

/**
 * description： <br>
 * createTime: 2018/3/169:54 <br>
 *
 * @author zzh
 */
@ThreadSafe
public class ValueLatch<V> {

    @GuardedBy("this")
    private V value = null;

    private final CountDownLatch done = new CountDownLatch(1);


    public V getValue() throws InterruptedException {
        done.await();
        synchronized (this) {
            return value;
        }
    }


    public boolean isSet() {
        return (done.getCount() == 0);
    }


    public synchronized void setValue(V newValue) {
        if (isSet()) {
            value = newValue;
            done.countDown();
        }
    }
}
