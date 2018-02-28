package advance.JVM.concurrent.test.test2;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * description： <br>
 * createTime: 2018/2/2810:13 <br>
 *
 * @author zzh
 */
public class App {

    @Test
    public void testPoolExpension() throws Exception {
        TestingThreadFactory threadFactory = new TestingThreadFactory();
        int MAX_SIZE = 10;
        ExecutorService es = Executors.newFixedThreadPool(MAX_SIZE);
        ((ThreadPoolExecutor) es).setThreadFactory(threadFactory);
        for (int i = 0; i < MAX_SIZE; i++) {
            es.execute(() -> {
                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        for (int i = 0; i < 20 && threadFactory.numCreated.get() < MAX_SIZE; i++) {
             Thread.sleep(100);
        }

        System.out.println(threadFactory.numCreated.get());
        assert threadFactory.numCreated.get() == MAX_SIZE;
        es.shutdownNow();
    }
}
