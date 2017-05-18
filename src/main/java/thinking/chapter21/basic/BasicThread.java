package thinking.chapter21.basic;

/**
 * Created by Administrator on 2016/3/26.
 */
public class BasicThread {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff()).start();
        }
        System.out.println("Waiting for liftoff");
    }
}
