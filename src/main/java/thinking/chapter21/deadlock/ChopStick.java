package thinking.chapter21.deadlock;

/**
 * Created by Administrator on 2016/4/18.
 */
public class ChopStick {
    private boolean taken = false;
    public synchronized void take () throws InterruptedException {
        while(taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}
