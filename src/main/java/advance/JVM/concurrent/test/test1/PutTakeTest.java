package advance.JVM.concurrent.test.test1;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description： <br>
 * createTime: 2018/2/2617:53 <br>
 *
 * @author zzh
 */
public class PutTakeTest {

    //线程池
    private static final ExecutorService es = Executors.newCachedThreadPool();

    //存入总和
    private final AtomicInteger putSum = new AtomicInteger(0);

    //取出总和
    private final AtomicInteger takeSum = new AtomicInteger(0);

    //栅栏
    private final CyclicBarrier barrier;

    //有界集合
    private final BoundedBuffer<Integer> bb;

    //每个生产和消费者操作次数
    private final int nTrails;

    //消费和生产者总数
    private final int nPairs;


    public PutTakeTest(int capacity, int nTrails, int nPairs) {
        this.bb = new BoundedBuffer<>(capacity);
        this.nTrails = nTrails;
        this.nPairs = nPairs;
        this.barrier = new CyclicBarrier(nPairs * 2 + 1);
    }


    public static void main(String[] args) {
        new PutTakeTest(10, 10, 40000).test();
        es.shutdown();
    }


    private void test() {
        try {
            for (int i = 0; i < nPairs; i++) {
                es.execute(new Producer());
                es.execute(new Consumer());
            }
            barrier.await();
            barrier.await();
            System.out.println(putSum.get() == takeSum.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    static int xorShift(int y) {
        y ^= (y << 6);
        y ^= (y >>> 21);
        y ^= (y << 7);
        return y;
    }

    class Producer implements Runnable {

        @Override
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int) System.nanoTime());
                int sum = 0;
                barrier.await();
                for (int i = nTrails; i > 0; --i) {
                    bb.put(seed);
                    sum += seed;
                    seed = xorShift(seed);
                }
                putSum .getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                barrier.await();
                int sum = 0;
                for (int i = nTrails; i > 0; --i) {
                    sum += bb.take();;
                }
                takeSum .getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
