package test.performancetest.concurrency.test1;

/**
 * description: <br>
 * createTime: 2017/10/2014:44 <br>
 *
 * @author zzh
 */
public class CounterClient implements Runnable {
    private volatile Counter c;
    private int num;

    public CounterClient(Counter c, int num) {
        this.c = c;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < num; i++) {
            c.increment();
        }
    }
}
