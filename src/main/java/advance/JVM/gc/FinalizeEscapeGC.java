package advance.JVM.gc;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/5/31.
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;
    public void isAlive() {
        System.out.println("I am still alive.");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalize method excuted.");
        SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        SAVE_HOOK = null;
        System.gc();

        TimeUnit.SECONDS.sleep(1);
        if(SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, I am dead :(");
        }

        SAVE_HOOK = null;
        System.gc();

        TimeUnit.SECONDS.sleep(1);
        if(SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, I am dead :(");
        }

        SAVE_HOOK = null;
        System.gc();

        TimeUnit.SECONDS.sleep(1);
        if(SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, I am dead :(");
        }
    }
}
