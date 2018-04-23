package advance.JVM.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * description： <br>
 * createTime: 2018/4/2314:22 <br>
 *
 * @author zzh
 */
public class ObjectNewTest {

    private static CountDownLatch cdl;

    private static CountDownLatch loopCdl;

    private static volatile boolean breakF = false;


    private static void breakLoop() {
        breakF = true;
    }


//    public static void main(String[] args) {
//        long start = System.currentTimeMillis();
//        long timeOut = 10 * 60 * 1000;
//
//        while (true) {
//            cdl = new CountDownLatch(1);
//            loopCdl = new CountDownLatch(100);
//            TestUtil.loop(() -> new Thread(() -> {
//                try {
//                    cdl.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Single s = Single.getInstance();
//
//                if (s.getA() == 0) {
//                    System.out.println(s.a);
//                    breakLoop();
//                }
//                loopCdl.countDown();
//            }).start(), 100);
//            cdl.countDown();
//
//            try {
//                loopCdl.await();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            if (breakF || (System.currentTimeMillis() - start) > timeOut) {
//                break;
//            }
//
//            Single.reset();
//        }
//        if (breakF) {
//            System.out.println("期望结果出现");
//        } else {
//            System.out.println("为发生期望结果");
//        }
//
//
//    }


    static class Single {

        private int a;

        private static Single instance;


        private Single() {
            this.a = new Random().nextInt(200) + 1;
        }


        public int getA() {
            return a;
        }


        public static Single getInstance() {

            if (instance == null) {
                synchronized (Single.class) {
                    if (instance == null) {
                        instance = new Single();
                    }
                }
            }
            return instance;
        }


        public static void reset() {
            instance = null;
        }
    }

}
