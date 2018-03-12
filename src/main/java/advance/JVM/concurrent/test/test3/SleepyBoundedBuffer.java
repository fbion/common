package advance.JVM.concurrent.test.test3;

import net.jcip.annotations.ThreadSafe;

/**
 * description： <br>
 * createTime: 2018/3/1216:11 <br>
 *
 * @author zzh
 */
@ThreadSafe
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    private static final long SLEEP_GRANULARITY = 100;

    public SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }


    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    return;
                }
                Thread.sleep(SLEEP_GRANULARITY);
            }
        }
    }

    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    return doTake();
                }
                Thread.sleep(SLEEP_GRANULARITY);
            }
        }
    }
}
