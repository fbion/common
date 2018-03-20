package advance.JVM.concurrent.test.test4;

/**
 * description： <br>
 * createTime: 2018/3/209:36 <br>
 *
 * @author zzh
 */
public abstract class PseudoRandom {
    public int nextInt() {
        return nextInt(Integer.MAX_VALUE);
    }

    public abstract int nextInt(int n);

    protected int calculateNextInt(int seed) {
        seed ^= (seed << 6);
        seed ^= (seed >>> 21);
        seed ^= (seed << 7);
        return seed;
    }
}
