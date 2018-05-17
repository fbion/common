import java.io.UnsupportedEncodingException;
import java.util.BitSet;

/**
 * description： <br>
 * createTime: 2018/3/2310:49 <br>
 *
 * @author zzh
 */
public class TestBitSet {

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        BitSet removeSet = new BitSet(10);
        for (int i = 0; i < 10; i++) {
            if(i % 3 == 2) {
                removeSet.set(i);
            }
        }
        System.out.println(removeSet);

        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "\t" + removeSet.nextClearBit(i));
        }
        System.out.println();
        for (int i = 0; i < 10 && i >= 0; i++) {
            System.out.println(i + "\t" + removeSet.nextSetBit(i));
        }
    }
}
