package test.performancetest.concurrency.test1;

/**
 * description: <br>
 * createTime: 2017/10/2014:44 <br>
 *
 * @author zzh
 */
public class StupidCounter implements Counter {
    private volatile long counter = 0;

    @Override
    public void increment() {
        counter++;
    }

    @Override
    public long getCounter() {
        return counter;
    }


    public static void main(String[] args) throws InterruptedException {
        TestDriver.test(new StupidCounter());
    }
}
/***
 Counter result: 45710416
 Time passed in ms:2006
 ***/