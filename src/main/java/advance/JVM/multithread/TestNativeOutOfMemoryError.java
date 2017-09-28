package advance.JVM.multithread;

import java.util.concurrent.CountDownLatch;

/**
 * 描述： <br>
 * 创建时间: 2017/9/289:30 <br>
 *
 * @author 周志辉
 */
public class TestNativeOutOfMemoryError {

    public static void main(String[] args) {

        for (int i = 0;; i++) {
            System.out.println("i = " + i);
            new Thread(new HoldThread()).start();
        }
    }

}

class HoldThread extends Thread {
    CountDownLatch cdl = new CountDownLatch(1);

    public HoldThread() {
        this.setDaemon(true);
    }

    public void run() {
        try {
            cdl.await();
        } catch (InterruptedException e) {
        }
    }
}
