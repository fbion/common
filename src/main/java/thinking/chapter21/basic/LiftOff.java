package thinking.chapter21.basic;

/**
 * Created by Administrator on 2016/3/26.
 */
public class LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;
    public LiftOff(){}
    public LiftOff(int countDown)
    {
        this.countDown = countDown;
    }
    public String status()
    {
        return "#" + id + "(" + (countDown > 0 ? countDown : "liftoff!") + "),";
    }
    @Override
    public void run() {
        while(countDown-- > 0)
        {
            System.out.print(status());
            Thread.yield();
        }
    }
}
