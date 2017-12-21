package advance.JVM.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * description: <br>
 * createTime: 2017/11/718:00 <br>
 *
 * @author zzh
 */
public class TestFutureCancel {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newCachedThreadPool();
        Future future = es.submit(() -> {
            System.out.println("start");
            throw new RuntimeException("exception");
        });
        TimeUnit.SECONDS.sleep(1);
        try{
            future.cancel(true);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
        System.out.println("over");
    }
}
