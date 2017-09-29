package advance.JVM.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic变量自增运算测试
 * @author zzh
 */
public class AtomicTest {

    public static AtomicInteger race = new AtomicInteger(0);

    private static final int THREADS_COUNT = 20;


    public static void increase() {
        race.incrementAndGet();
    }


    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            }
            );
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(race);
    }
}
