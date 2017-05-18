package thinking.chapter21;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/29.
 */
public class ActiveObjectDemo {
    private ExecutorService exec = Executors.newSingleThreadExecutor();
    private Random rand = new Random(47);
    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch (InterruptedException e) {
            System.out.println("sleep() interrupt");
        }
    }

    public Future<Integer> calculateInt(int x, int y) {
        return exec.submit(() -> {
            System.out.println("Starting " + x + " + " + y);
            pause(500);
            return x + y;
        });
    }

    public Future<Float> calculateFloat(float x, float y) {
        return exec.submit(() -> {
            System.out.println("Starting " + x + " + " + y);
            pause(500);
            return x + y;
        });
    }

    public void shutdown() {
        exec.shutdown();
    }

    public static void main(String[] args) {
        ActiveObjectDemo demo = new ActiveObjectDemo();
        List<Future<?>> results = new CopyOnWriteArrayList<>();
        for (float f = 0.0f; f < 1.0f; f += 0.2f) {
            results.add(demo.calculateFloat(f, f));
        }
        for (int i = 0; i < 5; i++) {
            results.add(demo.calculateInt(i, i));
        }
        System.out.println("All asynch calls made");
        while(results.size() > 0) {
            for (Future<?> future : results) {
                if(future.isDone()) {
                    try {
                        System.out.println(future.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    results.remove(future);
                }
            }
        }
    }
}
