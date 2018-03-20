package advance.JVM.concurrent.test.test3;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.concurrent.atomic.AtomicReference;

/**
 * description：用AtomicReferrence来实现符合某些不变性条件的多个变量间的的原子操作 <br>
 * createTime: 2018/3/208:40 <br>
 *
 * @author zzh
 */
public class CasNumberRange {

    @Immutable
    private static class IntPair {

        final int lower;    //不变性条件：lower <= upper;

        final int upper;


        public IntPair(int lower, int upper) {
            this.lower = lower;
            this.upper = upper;
        }
    }

    private final AtomicReference<IntPair> values = new AtomicReference<>(new IntPair(0, 0));


    public int getLower() {
        return values.get().lower;
    }


    public int getUpper() {
        return values.get().upper;
    }

    public void setLower(int val) {
        while(true) {
            IntPair oldV = values.get();
            if(val > oldV.upper) {
                throw new IllegalArgumentException("Can't set lower to " + val + " > upper");
            }
            IntPair newV = new IntPair(val, oldV.upper);
            if(values.compareAndSet(oldV, newV)) {
                return;
            }
        }
    }
}
