package advance.JVM.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述： <br>
 * 创建时间: 2017/9/2518:07 <br>
 *
 * @author 周志辉
 */
public class Test {
    static int i = 10;
    static boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ec = Executors.newCachedThreadPool();
        ec.submit(() -> {
            try {
                flag = true;
                TimeUnit.SECONDS.sleep(10);
                i = 11;
                flag =false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("over1");
        });
        ec.submit(() -> {
            while(flag) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(i);
            System.out.println("over2");
        });
        TimeUnit.SECONDS.sleep(12);
        ec.shutdownNow();
        System.out.println("main over");
    }
}
