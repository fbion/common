package advance.JVM.concurrent.test.test4;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * description： <br>
 * createTime: 2018/3/209:48 <br>
 *
 * @author zzh
 */
public class AtomicPseudoRandom extends PseudoRandom {
    private AtomicInteger seed;


    public AtomicPseudoRandom(int seed) {
        this.seed = new AtomicInteger(seed);
    }


    @Override
    public int nextInt(int n) {
        while(true) {
            int s = seed.get();
            int nextSeed = calculateNextInt(s);
            if(seed.compareAndSet(s, nextSeed)) {
                int remainder = s % n;
                return remainder > 0 ? remainder : remainder + n;
            }
        }
    }
}
