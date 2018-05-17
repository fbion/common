package ebook.thinking.chapter21.shareresources;

/**
 * Created by Administrator on 2016/3/28.
 */
public abstract class IntGenerator {
    private volatile boolean canceled = false;
    public abstract int next();
    public void cancel()
    {
        canceled = true;
    }
    public boolean isCanceled()
    {
        return canceled;
    }
}
