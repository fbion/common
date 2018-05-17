import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * description: <br>
 * createTime: 2018/5/911:55 <br>
 *
 * @author zzh
 */
public class Test3 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        exec.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("over");
        });
        exec.shutdown();
        while(!exec.isTerminated()) {
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("terminated");
    }
}
