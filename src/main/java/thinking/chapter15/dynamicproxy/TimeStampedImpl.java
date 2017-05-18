package thinking.chapter15.dynamicproxy;

import java.util.Date;

/**
 * Created by Administrator on 2016/3/5.
 */
public class TimeStampedImpl implements TimeStamped {
    private final long timeStamp;
    public TimeStampedImpl() {
        timeStamp = new Date().getTime();
    }
    public long getStamp(){
        return timeStamp;
    }
}
