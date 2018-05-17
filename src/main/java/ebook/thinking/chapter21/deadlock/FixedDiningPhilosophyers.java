package ebook.thinking.chapter21.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/18.
 */
public class FixedDiningPhilosophyers {
    public static void main(String[] args) throws InterruptedException {
        int ponder = 5;
        int size = 5;
        ExecutorService exec = Executors.newCachedThreadPool();
        ChopStick[] sticks = new ChopStick[size];
        for (int i = 0; i < size; i++) {
            sticks[i] = new ChopStick();
        }
        for (int i = 0; i < size; i++) {
            if(i < size - 1){
                exec.execute(new Philosopher(i, ponder, sticks[i], sticks[(i + 1) % size]));
            }
            else{
                exec.execute(new Philosopher(i, ponder, sticks[0], sticks[i]));
            }
        }
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
