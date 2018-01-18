/**
 * description： <br>
 * createTime: 2018/1/816:08 <br>
 *
 * @author zzh
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        int i = 100;
        double d = 0.234;
        double result = i * d;
        System.out.println(String.format("%.2f", result));
        System.out.println(String.format("%.5f", result));
    }
}