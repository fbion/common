package advance.JVM.gc;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * description: <br>
 * createTime: 2018/4/2811:01 <br>
 * -XX:+PrintGCDetails
 * -verbose:gc
 * @author zzh
 */
public class TestDeadLoopGC {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true) {
                try {
                    for (int i = 0; i < 10000000; i++) {
                        Date date = new Date();
                    }
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }){{setDaemon(true);}}.start();
        TimeUnit.SECONDS.sleep(20);
        System.out.println("main over");
    }
}
