package ebook.thinking.chapter21.basic;


import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/3/26.
 */
public class Daemons{
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.print("d.isdaemon() = " + d.isDaemon() + ", ");
        TimeUnit.SECONDS.sleep(1);
    }
}

class Daemon  implements Runnable
{
    private Thread[] t = new Thread[10];
    @Override
    public void run() {
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(new DaemonFromFactory());
            t[i].start();
            System.out.println("DaemonSpawn " + i + " started, ");
        }
        for (int i = 0; i < t.length; i++) {
            System.out.print("t[" + i + "].isdaemon() = " + t[i].isDaemon() + ", ");
        }
        while(true)
        {
            Thread.yield();
        }
    }
}

class DaemonSpawn implements Runnable
{
    @Override
    public void run() {
        while(true)
        {
            Thread.yield();
        }
    }
}