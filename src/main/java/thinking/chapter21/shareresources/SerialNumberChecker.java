package thinking.chapter21.shareresources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/1.
 */
public class SerialNumberChecker {
    private static final int SIZE = 10;
    private static CircularSet serials = new CircularSet(1000);
    private static ExecutorService exec = Executors.newCachedThreadPool();
    static class SerialChecker implements Runnable
    {
        @Override
        public void run() {
            while(true)
            {
                int serial = SerialNumberGenertor.nextSerialNumber();
                if(serials.contains(serial))
                {
                    System.out.println("Duplicate : " + serial);
                    System.exit(0);
                }
                serials.add(serial);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new SerialChecker());
        }
        if(args.length > 0)
        {
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
            System.out.println("No duplicates detected.");
            System.exit(0);
        }
    }
}

class CircularSet {
    private int[] array;
    private int len;
    private int index = 0;

    public CircularSet(int len) {
        array = new int[len];
        this.len = len;
        for (int i = 0; i < len; i++) {
            array[i] = -1;
        }
    }

    public synchronized void add(int i)
    {
        array[index] = i;
        index = ++index % len;
    }

    public synchronized boolean contains(int val)
    {
        for (int i = 0; i < len; i++) {
            if(array[i] == val)
            {
                return true;
            }
        }
        return false;
    }
}
