package advance.JVM.concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * description: Timer发生异常时无法正确处理，反而会认为任务都已经被取消，当再增加定时任务时会报取消的异常<br>
 * createTime: 2017/10/2318:08 <br>
 *
 * @author zzh
 */
public class OutOfTime {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(), 1);
        TimeUnit.SECONDS.sleep(1);
        timer.schedule(new ThrowTask(), 1);
        TimeUnit.SECONDS.sleep(5);
    }

    static class ThrowTask extends TimerTask {

        @Override
        public void run() {
                   throw new RuntimeException();
        }
    }
}
