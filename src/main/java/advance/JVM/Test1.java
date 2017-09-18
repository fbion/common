package advance.JVM;

/**
 * 描述： <br>
 * 创建时间: 2017/9/1817:58 <br>
 *  -XX:+PrintCompilation -XX:+PrintInlining
 * @author 周志辉
 */
public class Test1 {

    public static final int NUM = 15000;

    public static int doubleValue(int i) {
        for (int j = 0; j < 20000; j++) {
            ;
        }
        return i * 2;
    }

    public static long calcSum() {
        long sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += doubleValue(i);
        }
        return sum;
    }


    public static void main(String[] args) {
        for (int i = 0; i < NUM; i++) {
            calcSum();
        }
    }
}
