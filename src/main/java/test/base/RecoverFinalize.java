package test.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/5/18.
 */
public class RecoverFinalize {
    public static RecoverFinalize recover;
    private int id = counter++;
    private static int counter = 1;

    @Override
    protected void finalize() throws Throwable {
        System.out.println("In finalize");
        recover = this;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " : " + id;
    }

    public static void main(String[] args) throws InterruptedException {
        recover = new RecoverFinalize();
        Reference<RecoverFinalize> refer = new WeakReference<RecoverFinalize>(recover);
        recover = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        recover = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(refer.get());
    }
}
