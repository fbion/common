package test.performancetest;

/**
 * Created by Administrator on 2016/3/29.
 */
public class TestTry {
    public static void main(String[] args) {
        long start = System.nanoTime();
        double d = 1;
        for (int i = 0; i < 100000000; i++) {
            d = d + (Math.PI + Math.E) / d;
        }
        System.out.println("duration1: " + (System.nanoTime() - start));

        start = System.nanoTime();
        d = 1;
        for (int i = 0; i < 100000000; i++) {
            try {
                d = d + (Math.PI + Math.E) / d;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("duration2: " + (System.nanoTime() - start));
    }
}
