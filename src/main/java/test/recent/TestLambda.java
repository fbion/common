package test.recent;

/**
 * Created by Administrator on 2015/12/10.
 */
public class TestLambda {
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("1");
        }).start();
    }
}
