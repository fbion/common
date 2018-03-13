package advance.JVM.concurrent.test.test3;

/**
 * description： <br>
 * createTime: 2018/3/1216:23 <br>
 *
 * @author zzh
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {

    public BoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws InterruptedException {
        while(isFull()) {
            wait();
        }
        boolean wasEmpty = isEmpty();
        doPut(v);
        if(wasEmpty) {
            notifyAll();
        }
    }

    public synchronized V take() throws InterruptedException {
        while(isEmpty()) {
            wait();
        }
        boolean wasFul = isFull();
        V v = doTake();
        if(wasFul) {
            notifyAll();
        }
        return v;
    }
}
