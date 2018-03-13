package advance.JVM.concurrent.test.test3;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * description： <br>
 * createTime: 2018/3/1310:01 <br>
 *
 * @author zzh
 */
@ThreadSafe
public class ThreadGate {

    @GuardedBy("this")
    private boolean isOpen;

    @GuardedBy("this")
    private int gerneration;

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void open() {
        ++gerneration;
        isOpen = true;
        notifyAll();
    }

    public synchronized void await() throws InterruptedException {
        int arrivalGeneration = gerneration;
        while(!isOpen && arrivalGeneration == gerneration) {
            wait();
        }
    }
}
