package ebook.thinking.chapter21.shareresources;

/**
 * Created by Administrator on 2016/4/1.
 */
public class SyncObject {
    public static void main(String[] args) {
        final DualSynch ds = new DualSynch();
        new Thread(){
            @Override
            public void run() {
                ds.f();
            }
        }.start();
        ds.g();
    }
}

class DualSynch
{
    private Object syncObject = new Object();
    public synchronized void f()
    {
        for (int i = 0; i < 5; i++) {
            System.out.println("f()");
            Thread.yield();
        }
    }
    public void g()
    {
        synchronized (syncObject)   //锁this将产生阻塞
        {
            for (int i = 0; i < 5; i++) {
                System.out.println("g()");
                Thread.yield();
            }
        }
    }
}
