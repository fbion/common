package test.recent;

import java.math.BigDecimal;
import java.text.ParseException;

/**
 * Created by Administrator on 2016/3/5.
 */
public class Test2 {
    public static void main(String[] args) throws ParseException {
//        ZoneId america = ZoneId.of("America/New_York");
//        ZoneId zone0 = ZoneId.of("UTC+00:00");
//        ZoneId zone8 = ZoneId.of("UTC+08:00");
//        ZoneOffset offset = ZoneOffset.UTC;
//        LocalDateTime nowOfHere = LocalDateTime.now();
//        ZonedDateTime timePoint8 =ZonedDateTime.of(nowOfHere, zone8);
//        ZonedDateTime timePoint0 = timePoint8.withZoneSameInstant(zone0);
//
//        System.out.println(timePoint8);
//        System.out.println(timePoint0);

        double f1 = 299.0F/31;
        double f2 = f1 * 31;
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f2==299.0d);
        if(f2 - 299.0 < 0.0001){
            System.out.println("equals");
        }

        BigDecimal bd1 = new BigDecimal(299);
        BigDecimal bd2 = bd1.divide(new BigDecimal(31), 5, BigDecimal.ROUND_CEILING);
        BigDecimal bd3 = bd2.multiply(new BigDecimal(31));
        System.out.println(bd1);
        System.out.println(bd2);
        System.out.println(bd3);
        System.out.println(bd1.equals(bd3));
    }
}
