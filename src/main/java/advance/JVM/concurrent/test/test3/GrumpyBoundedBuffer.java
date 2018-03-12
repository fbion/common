package advance.JVM.concurrent.test.test3;

/**
 * description： <br>
 * createTime: 2018/3/1216:02 <br>
 *
 * @author zzh
 */
public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V>{

    public GrumpyBoundedBuffer(int size) {
        super(size);
    }

    //队列满时存放抛异常，而不是阻塞，这导致，代码丑陋，而且调用者需要自行捕获异常重试
    public synchronized void put(V v) {
        if(isFull()) {
            throw new RuntimeException("Buffer is rull");
        }
        doPut(v);
    }

    //同上，调用者需要自行捕获异常重试
    public synchronized void take() {
        if(isEmpty()) {
            throw new RuntimeException("Buffer is rull");
        }
        doTake();
    }
}
