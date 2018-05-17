import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * description: <br>
 * createTime: 2018/5/711:02 <br>
 *
 * @author zzh
 */
public class Test2 {
    public static final int COUNT = 10_000_000;
    public static final Set<Integer> resultSet = new TreeSet<>();


    static class Inner{
        public int i = 0;

        public void test() throws InterruptedException {
            Thread t1 = new Thread(() -> {
                for (int k = 0; k < 100; k++) {
                    i++;
                }
            });
            Thread t2 = new Thread(() -> {
                for (int k = 0; k < 100; k++) {
                    i++;
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            if(!t1.isAlive() && !t2.isAlive()) {
                resultSet.add(i);
            } else {
                System.out.println("------------------------------");
            }
        }
    }



    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(50);
        long start = System.currentTimeMillis();
        for (int i = 0; i < COUNT; i++) {
            exec.submit(() -> {
                try {
                    new Inner().test();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
        while(!exec.isTerminated()) {
            System.out.println("not terminated");
            TimeUnit.SECONDS.sleep(300);
        }
        System.out.println(resultSet);
        System.out.println("duration : " + (System.currentTimeMillis() - start));
    }
}
