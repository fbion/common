package thinking.chapter21.shareresources;

/**
 * Created by Administrator on 2016/3/29.
 */
public class SynchronizedEventGenerator extends IntGenerator {
    private int currentEventValue = 0;
    @Override
    public synchronized int next() {
        ++currentEventValue;
        Thread.yield();
        ++currentEventValue;
        return currentEventValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEventGenerator());
    }
}
