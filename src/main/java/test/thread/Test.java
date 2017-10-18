package test.thread;

import java.util.concurrent.TimeUnit;

/**
 * description： <br>
 * createTime: 2017/10/1814:53 <br>
 *
 * @author zzh
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        Thread thread1 = new Thread(() -> {
            try {
                synchronized (o) {
                    while (true) {
                        int i = 0;
                        System.out.println(i);
                        o.wait();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
//                Thread.currentThread().interrupt();
            }
            System.out.println("thread over");
        }){{setDaemon(true);}};
        thread1.start();
        TimeUnit.SECONDS.sleep(3);
        o.notify();
        System.out.println("main over");
        TimeUnit.SECONDS.sleep(3);
    }
}
