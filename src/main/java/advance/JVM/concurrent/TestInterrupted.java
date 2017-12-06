package advance.JVM.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * description: 当调用了一个线程的interrupt方法时，那个线程处于阻塞方法中时，会抛出InterruptedException,
 * 否则只会把中断标志置true，并不会导致抛异常，代码会正常执行
 * 执行的代码中如果有阻塞方法，阻塞方法通常会在一开始就检查中断标志，发现为true，则抛异常，如test2方法所示<br>
 * createTime: 2017/10/2416:51 <br>
 *
 * @author zzh
 */
public class TestInterrupted {

    public static void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("sleep");
            try {
                while (true) {
                    System.out.println("sleep on");
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupted");
                Thread.currentThread().interrupt();
                System.out.println("----interrupted: " + Thread.interrupted());
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(2);
        thread.interrupt();
        System.out.println("interrupted: " + thread.isInterrupted());
    }


    public static void test2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("begin counting");
            try {
                int sum = 0;
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    sum += i;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("----interrupted");
                Thread.currentThread().interrupt();
                System.out.println("----interrupted: " + Thread.interrupted());
            }
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(10);
        thread.interrupt();
        System.out.println("interrupted: " + thread.isInterrupted());
    }


    public static void main(String[] args) throws InterruptedException {
        test2();
    }
}