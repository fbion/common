package advance.JVM;

import java.util.concurrent.TimeUnit;

/**
 * description： <br>
 * createTime: 2018/3/3010:25 <br>
 *
 * @author zzh
 */
public class TestShutdown {

    public static void main(String[] args) {
        System.out.println("main begin");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("In shutdown hook");
        }));
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println("new Thread over.");
//                System.exit(0);
                Runtime.getRuntime().halt(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            System.out.println("main thread sleep");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("main over");
        }
    }
}
