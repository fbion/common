package work.qizhi.test;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/3/12.
 */
public class TestDouble {
    private static final long MILLSECONDS_OF_DAY = 24*60*60*1000;

    public static int test(int val){
        return val + val/10*2;
    }

    public static void main(String[] args) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.set(Calendar.DAY_OF_MONTH, 10);
//        cal1.set(Calendar.SECOND, 50);
        cal1.set(Calendar.SECOND, 52);
        System.out.println(new Date(cal1.getTimeInMillis()));
        System.out.println(new Date(cal2.getTimeInMillis()));
        double leftDays = Math.ceil((cal2.getTimeInMillis() - cal1.getTimeInMillis())/MILLSECONDS_OF_DAY);
        System.out.println(leftDays);
    }
}
