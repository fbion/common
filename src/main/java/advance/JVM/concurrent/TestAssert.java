package advance.JVM.concurrent;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * 描述： <br>
 * 创建时间: 2017/9/2610:05 <br>
 *
 * @author 周志辉
 */
public class TestAssert implements Serializable{

    public static void main(String[] args) throws InterruptedException {
        Holder holder = new Holder(0);
        for (int i = 0; i < 100; i++) {
            int temp = i;
            new Thread(() -> {
                int t = 0;
                while(true) {
                    try {
                        holder.setN(temp + t++);
                        TimeUnit.MILLISECONDS.sleep(100);
                        holder.assertN();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }){{setDaemon(true);}}.start();
        }
        TimeUnit.SECONDS.sleep(500);
        System.out.println("over");
    }
}
class Holder{
    private int n;
    public Holder(int n) {
        this.n = n;
    }
    public void assertN() {
        if(n != n) {
            throw new AssertionError("n != n");
        }
    }
    public Holder setN(int n) {
        this.n = n;
        return this;
    }
}