package test.performancetest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/4/19.
 */
public class TestLock {
    private Lock lock = new ReentrantLock();
    public void test1() {
        for (int i = 0; i < 10000; i++) {
            synchronized (this){}
        }
    }
    public void test2(){
        for (int i = 0; i < 10000; i++) {
            lock.lock();
            try{}finally {
                lock.unlock();
            }
        }
    }
    public void test3(){
        for (int i = 0; i < 10000; i++) {}
    }
    public static void main(String[] args) {
        TestLock test = new TestLock();
        long start = System.nanoTime();
        test.test1();
        long duration1 = System.nanoTime() - start;
        start = System.nanoTime();
        test.test2();
        long duration2 = System.nanoTime() - start;
        start = System.nanoTime();
        test.test3();
        long duration3 = System.nanoTime() - start;
        System.out.println("test1 : " + duration1);
        System.out.println("test2 : " + duration2);
        System.out.println("test3 : " + duration3);
    }
}
