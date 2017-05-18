package thinking.chapter21.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/3/26.
 */
public class FixedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i <5 ; i++) {
            exec.execute(new LiftOff());
        }
        exec.shutdown();
        System.out.println("\n" + exec.getClass().getName());
    }
}
