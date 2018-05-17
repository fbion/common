package ebook.thinking.chapter21.stopthread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/4/5.
 */
public class Interrupting2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Block2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Issuing t.interrupt()");
        t.interrupt();
    }
}

class BlockedMutex
{
    private Lock lock = new ReentrantLock();

    public BlockedMutex() {
        lock.lock();
    }

    public void f()
    {
        try{
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
//            e.printStackTrace();
            System.out.println("Interrupted from lock acquisition in f()");
        }
    }
}

class Block2 implements Runnable
{
    BlockedMutex blocked = new BlockedMutex();

    @Override
    public void run() {
        System.out.println("Waiting for f() in BlockedMutex");
        blocked.f();
        System.out.println("Broken out of blocked call");
    }
}
