package ebook.thinking.chapter21.elements.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/25.
 */
public class TestSemaphore {
    static Semaphore semaphore = new Semaphore(2, true);

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 4; i++) {
            int temp = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(temp);
                    TimeUnit.SECONDS.sleep(2);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }

    }
}
