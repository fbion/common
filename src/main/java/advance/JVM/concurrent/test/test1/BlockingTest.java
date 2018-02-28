package advance.JVM.concurrent.test.test1;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * description： <br>
 * createTime: 2018/2/2417:11 <br>
 *
 * @author zzh
 */
public class BlockingTest {

    @Test
    public void testTakeBlockwhenEmpty() {
        int time = 5000;
        final BoundedBuffer<Integer> bb = new BoundedBuffer<>(10);
        Thread taker = new Thread(){
            @Override
            public void run() {
                try{
                    int unused = bb.take();
                } catch (InterruptedException success) {
                }
            }
        };
        try {
            taker.start();
            TimeUnit.MILLISECONDS.sleep(time);
            taker.interrupt();
            taker.join(time);
            assert !taker.isAlive();
        } catch (Exception unexpected) {
            System.out.println("fail");
        }
    }
}
