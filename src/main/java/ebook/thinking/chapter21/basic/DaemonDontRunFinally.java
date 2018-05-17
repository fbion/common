package ebook.thinking.chapter21.basic;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/3/26.
 */
public class DaemonDontRunFinally {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
        TimeUnit.SECONDS.sleep(2);
    }
}

class ADaemon implements Runnable
{
    @Override
    public void run()
    {
        System.out.println("In run");
        try
        {
            System.out.println("Starting ADaemon");
            TimeUnit.SECONDS.sleep(1);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        } finally
        {
            System.out.println("finally");
        }
    }
}