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
        for (int i = 1; i <= 20; i++) {
            Integer i1 = new Integer(i);
            Integer i2 = i;
            System.out.println( i + " : " + (i1 == i2));
        }
    }
}
