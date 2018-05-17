package ebook.thinking.chapter21.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/18.
 */
public class Philosopher implements Runnable {
    private ChopStick left;
    private ChopStick right;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);
    private void pause() throws InterruptedException {
        if(ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    public Philosopher(int id, int ponderFactor, ChopStick right, ChopStick left) {
        this.id = id;
        this.ponderFactor = ponderFactor;
        this.right = right;
        this.left = left;
    }

    @Override
    public void run() {
        try {
            while(! Thread.interrupted()) {
                System.out.println(this + " " + "ebook/thinking");
                pause();
                System.out.println(this + " " + "grabbing right");
                right.take();
                System.out.println(this + " " + "grabbing left");
                left.take();
                System.out.println(this + " " + "eating");
                pause();
                right.drop();
                left.drop();

            }
        } catch (InterruptedException e) {
            System.out.println(this + " " + "exit via interrupt");
        }
    }

    public String toString() {
        return "Philosopher " + id;
    }
}
