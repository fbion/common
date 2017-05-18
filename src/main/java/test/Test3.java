package test;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/8/10.
 */
public class Test3 {
    public static String test(Calendar date1, Calendar date2) {
        long sub = (date1.getTimeInMillis() - date2.getTimeInMillis()) / (24 * 60 * 60 * 1000);
        System.out.println(sub);
        Calendar large = sub > 0 ? date1 : date2;
        Calendar small = sub > 0 ? date2 : date1;
        sub = Math.abs(sub);
        return null;
    }
    public static void main(String[] args) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.YEAR, 2015);
        calendar1.set(Calendar.MONTH, 2);
        calendar1.set(Calendar.DAY_OF_MONTH, 16);
        Calendar calendar2 = (Calendar) calendar1.clone();
        calendar2.set(Calendar.YEAR, 2016);
        calendar2.set(Calendar.MONTH, 8);
        calendar2.set(Calendar.DAY_OF_MONTH, 13);
        System.out.println(test(calendar1, calendar2));
    }
}
