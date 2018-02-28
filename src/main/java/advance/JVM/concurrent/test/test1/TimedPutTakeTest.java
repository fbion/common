package advance.JVM.concurrent.test.test1;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description： <br>
 * createTime: 2018/2/2810:35 <br>
 *
 * @author zzh
 */
public class TimedPutTakeTest {

    //线程池
    private static final ExecutorService es = Executors.newCachedThreadPool();

    //存入总和
    private final AtomicInteger putSum = new AtomicInteger(0);

    //取出总和
    private final AtomicInteger takeSum = new AtomicInteger(0);

    //栅栏
    private final CyclicBarrier barrier;

    private final BarrierTimer timer;

    //有界集合
    private final BoundedBuffer<Integer> bb;

    //每个生产和消费者操作次数
    private final int nTrails;

    //消费和生产者总数
    private final int nPairs;


    public TimedPutTakeTest(int capacity, int nPairs, int nTrails) {
        this.bb = new BoundedBuffer<>(capacity);
        this.nTrails = nTrails;
        this.nPairs = nPairs;
        this.timer = new BarrierTimer();
        this.barrier = new CyclicBarrier(nPairs * 2 + 1, timer);
    }


    public static void main(String[] args) throws InterruptedException {
        int tpt = 100000;
        for (int cap = 1; cap < 1000; cap *= 10) {
            System.out.println("Capacity: " + cap);
            for (int pairs = 1; pairs <= 128; pairs *= 2) {
                TimedPutTakeTest test = new TimedPutTakeTest(cap, pairs, tpt);
                System.out.print("Pairs: " + pairs + "\t");
                test.test();
                System.out.print("\t\t");
                Thread.sleep(1000);
                test.test();
                System.out.println();
                Thread.sleep(1000);
            }
        }
        es.shutdown();
    }


    private void test() {
        try {
            timer.clear();
            for (int i = 0; i < nPairs; i++) {
                es.execute(new TimedPutTakeTest.Producer());
                es.execute(new TimedPutTakeTest.Consumer());
            }
            barrier.await();
            barrier.await();
            long nsPerIterm = timer.getTime() / (nPairs * (long) nTrails);

            System.out.print("Throughput: " + nsPerIterm + "ns/item");
            assert (putSum.get() == takeSum.get());
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
                putSum.getAndAdd(sum);
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
                    sum += bb.take();
                    ;
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public class BarrierTimer implements Runnable {

        private boolean started;

        private long startTime;

        private long endTime;


        public synchronized void run() {
            long t = System.nanoTime();
            if (!started) {
                started = true;
                startTime = t;
            } else {
                endTime = t;
            }
        }

        public synchronized void clear() {
            started = false;
        }

        public synchronized long getTime() {
            return endTime - startTime;
        }
    }
}
