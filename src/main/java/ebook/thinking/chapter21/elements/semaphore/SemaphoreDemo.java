package ebook.thinking.chapter21.elements.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/20.
 */
public class SemaphoreDemo {
    final static int SIZE = 25;
    public static void main(String[] args) throws InterruptedException {
        final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new CheckoutTask<Fat>(pool));
        }
        System.out.println("All CheckoutTask created.");
        List<Fat> list = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Fat f = pool.checkOut();
            System.out.print(i + " : main() thread check out ");
            f.operation();
            list.add(f);
        }
        Future<?> block = exec.submit(() -> {
            try {
                pool.checkOut();
            }catch (InterruptedException e) {
                System.out.println("checkout interrupted");
            }
        });
        TimeUnit.SECONDS.sleep(2);
        block.cancel(true);
        System.out.println("Checking in objects in " + list);
        for (Fat fat : list) {
            pool.checkIn(fat);
        }
        for (Fat fat : list) {
            pool.checkIn(fat);
        }
        exec.shutdown();
    }
}

class CheckoutTask<T> implements Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this + " checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this + " checked in " + item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String toString() {
        return "CheckoutTask " + id + " ";
    }
}
