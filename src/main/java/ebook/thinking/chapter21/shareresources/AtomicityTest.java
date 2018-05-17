package ebook.thinking.chapter21.shareresources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/4/1.
 */
public class AtomicityTest implements Runnable {
    private int i = 0;

    /**
     * getI方法没有同步且，return i不是原子操作，可能导致i的中间状态被返回了
     * @return
     */
    public int getI() {
        return i;
    }

    private synchronized void evenIncrement()
    {
        i++;
        i++;
    }

    @Override
    public void run() {
        while(true)
        {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        while(true)
        {
            int val = at.getI();
            if(val % 2 != 0)
            {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
