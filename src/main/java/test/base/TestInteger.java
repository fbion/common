package test.base;

/**
 * Created by Administrator on 2016/6/21.
 */
public class TestInteger {
    /**
     * -XX:AutoBoxCacheMax=256
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 255; i <= 257; i++) {
            Integer i1 = i;
            Integer i2 = i;
            System.out.println( i + " : " + (i1 == i2));
        }
    }
}
