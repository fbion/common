package ebook.thinking.chapter21.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/18.
 */
public class HorseRace {
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<>();
    ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public HorseRace(int nHorse, final int pause) {
        barrier = new CyclicBarrier(nHorse, () -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < FINISH_LINE; i++) {
                sb.append("=");
            }
            System.out.println(sb);
            for (Horse horse : horses) {
                System.out.println(horse.tracks());
            }
            for (Horse horse : horses) {
                if(horse.getStrides() >= FINISH_LINE) {
                    System.out.println(horse + "won!");
                    exec.shutdownNow();
                    return;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        for (int i = 0; i < nHorse; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        new HorseRace(7, 200);
    }
}

class Horse implements Runnable {
    private static int count = 0;
    private final int id = count++;
    private int strides = 0;
    private static Random rand = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                synchronized (this) {
                    strides += rand.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}
