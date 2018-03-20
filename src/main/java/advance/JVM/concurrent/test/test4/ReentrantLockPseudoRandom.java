package advance.JVM.concurrent.test.test4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description： <br>
 * createTime: 2018/3/209:28 <br>
 *
 * @author zzh
 */
public class ReentrantLockPseudoRandom extends PseudoRandom {
    private final Lock lock = new ReentrantLock();

    private int seed;


    public ReentrantLockPseudoRandom(int seed) {
        this.seed = seed;
    }


    @Override
    public int nextInt(int n) {
        lock.lock();
        try {
            int s = seed;
            seed = calculateNextInt(s);
            int remainder = s % n;
            return remainder > 0 ? remainder : remainder + n;
        } finally {
            lock.unlock();
        }
    }

}
