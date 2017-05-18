package thinking.chapter21.shareresources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/3/28.
 */
public class EvenChecker implements Runnable {
    private IntGenerator intGenerator;
    private final int id;

    public EvenChecker(IntGenerator intGenerator, int id) {
        this.intGenerator = intGenerator;
        this.id = id;
    }

    @Override
    public void run() {
        while(!intGenerator.isCanceled())
        {
            int val = intGenerator.next();
            if(val % 2 != 0)
            {
                System.out.print(Thread.currentThread() + " : ");
                System.out.println(val + " not even");
                intGenerator.cancel();
            }
        }
        System.out.println(Thread.currentThread() + " : closed.");
    }

    public static void test(IntGenerator gp, int count){
        System.out.println("Press Control-C to exit.");
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            exec.execute(new EvenChecker(gp, i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator gp) {
        test(gp, 10);
    }
}
