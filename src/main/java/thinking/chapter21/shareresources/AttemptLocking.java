package thinking.chapter21.shareresources;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2016/4/1.
 */
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed()
    {
        boolean captured = lock.tryLock();
        try
        {
            System.out.println("tryLock() : " + captured);
        }
        finally
        {
            if(captured)
            {
                lock.unlock();
            }
        }
    }

    public void timed()
    {
        boolean captured = false;
        try
        {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            System.out.println("lock.tryLock(2, TimeUnit.SECONDS) : " + captured);
        }finally
        {
            if(captured)
            {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final AttemptLocking al = new AttemptLocking();
        al.untimed();
        al.timed();
        new Thread()
        {
            {
                setDaemon(true);
            }
            @Override
            public void run() {
                al.lock.lock();
                System.out.println("acquired");
            }
        }.start();
        Thread.yield();
//        TimeUnit.SECONDS.sleep(1);
        al.untimed();
        al.timed();
    }
}
