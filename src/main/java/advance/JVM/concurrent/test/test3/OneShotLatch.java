package advance.JVM.concurrent.test.test3;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * description： <br>
 * createTime: 2018/3/1517:12 <br>
 *
 * @author zzh
 */
@ThreadSafe
public class OneShotLatch {

    private final Sync sync = new Sync();


    public void signal() {
        sync.releaseShared(0);
    }


    public void await() throws InterruptedException {
        sync.acquireInterruptibly(0);
    }


    class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }


        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }
}
