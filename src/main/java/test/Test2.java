package test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 2016/9/5.
 */
public class Test2 {
    public static void test() {
        for (int i = 10; Math.abs(i) < 11; i--) {
            for (int j = 0; j <= Math.abs(i); j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void test1(String monthAndYear) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(new SimpleDateFormat("yyyyMM").parse(monthAndYear));
        c.add(Calendar.MONTH, 2);
        c.add(Calendar.DAY_OF_MONTH, -1);
        System.out.println(new Date(c.getTimeInMillis()));
    }
    public static void main(String[] args) throws ParseException {
//        test();
        test1("201606");
    }
}
