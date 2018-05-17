package ebook.thinking.chapter21.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/3/26.
 */
public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i <5 ; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
        System.out.println("\n" + exec.getClass().getName());
    }
}
