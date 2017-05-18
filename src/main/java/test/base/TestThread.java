package test.base;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/18.
 */
public class TestThread {
    public static void main(String[] args) {
        Timer timer = new Timer();
        new Thread(() -> timer.add(Thread.currentThread().getName())).start();
        new Thread(() -> timer.add2(Thread.currentThread().getName())).start();
    }
}
class Timer {
    private static int num = 0;
    public void add(String name) {
        synchronized (this) {
            num++;
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + ",你是第" + num + "个访问的线程");
        }
    }
    public void add2(String name) {
        num++;
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + ",你是第" + num + "个访问的线程");
    }
}