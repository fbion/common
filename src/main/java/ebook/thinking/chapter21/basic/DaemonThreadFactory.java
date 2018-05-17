package ebook.thinking.chapter21.basic;

import java.util.concurrent.ThreadFactory;

/**
 * Created by Administrator on 2016/3/26.
 */
public class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
