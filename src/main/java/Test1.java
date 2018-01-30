import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * description： <br>
 * createTime: 2018/1/2311:01 <br>
 *
 * @author zzh
 */
public class Test1 {

    static ExecutorService es = Executors.newCachedThreadPool();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main Thread : " + Thread.currentThread().getId());
        Future<String> future = es.submit(() -> {
            System.out.println("task Thread : " + Thread.currentThread().getId());
            TimeUnit.SECONDS.sleep(10);
            return "hello world";
        });
        System.out.println("Thread : " + Thread.currentThread().getId());
        System.out.println(future.get());
        System.out.println(future.getClass());
        es.shutdown();
    }
}
