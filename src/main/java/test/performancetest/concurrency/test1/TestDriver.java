package test.performancetest.concurrency.test1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * description: <br>
 * createTime: 2017/10/2014:39 <br>
 *
 * @author zzh
 */
public class TestDriver {

    public static void test(Counter counter) throws InterruptedException {
        int NUM_OF_THREADS = 1000;
        int NUM_OF_INCREMENTS = 100000;
        ExecutorService service = Executors.newFixedThreadPool(NUM_OF_THREADS);
        long before = System.currentTimeMillis();
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            service.submit(new CounterClient(counter, NUM_OF_INCREMENTS));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.MINUTES);
        long after = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());
        System.out.println("Time passed in ms:" + (after - before));
    }


    public static void main(String[] args) throws Exception {
        System.out.println("StupidCounter");
        TestDriver.test(new StupidCounter());

        System.out.println("SyncCounter");
        TestDriver.test(new SyncCounter());

        System.out.println("LockCounter");
        TestDriver.test(new LockCounter());

        System.out.println("AtomicCounter");
        TestDriver.test(new AtomicCounter());

        System.out.println("CASCounter");
        TestDriver.test(new CASCounter());
    }
}

/**
 StupidCounter
 Counter result: 49019236
 Time passed in ms:2191
 SyncCounter
 Counter result: 100000000
 Time passed in ms:4331
 LockCounter
 Counter result: 100000000
 Time passed in ms:4230
 AtomicCounter
 Counter result: 100000000
 Time passed in ms:2589
 CASCounter
 Counter result: 100000000
 Time passed in ms:9660
 **/