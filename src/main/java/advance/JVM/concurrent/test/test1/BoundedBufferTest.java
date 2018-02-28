package advance.JVM.concurrent.test.test1;


import org.junit.Test;

/**
 * description： <br>
 * createTime: 2018/2/2417:02 <br>
 *
 * @author zzh
 */
public class BoundedBufferTest {

    @Test
    public void testIsEmptyConstructed() {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        assert bb.isEmpty();
        assert !bb.isFull();
    }


    @Test
    public void testIsFullAfterPuts() throws InterruptedException {
        BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            bb.put(i);
        }
        assert !bb.isEmpty();
        assert bb.isFull();
    }
}
