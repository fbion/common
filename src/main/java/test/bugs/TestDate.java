package test.bugs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * description： <br>
 * createTime: 2017/12/2115:52 <br>
 *
 * @author zzh
 */
public class TestDate {
    private static final Set<Integer> bigMonthSet = new HashSet<>();
    private static final Set<Integer> smallMonthSet = new HashSet<>();
    static {
        bigMonthSet.addAll(Arrays.asList(1, 3, 5, 7, 8, 10, 12));
        smallMonthSet.addAll(Arrays.asList(4, 6, 9, 11));
    }
    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 100 != 0 && year % 4 == 0);
    }
    public static int getMaxDays(int year, int month) {
        if(bigMonthSet.contains(month)) {
            return 31;
        }
        if(smallMonthSet.contains(month)) {
            return 30;
        }
        if(isLeapYear(year)) {
            return 29;
        }
        return 28;
    }

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM-dd HH:mm:ss");
        int startYear = 1900;
        for (int i = 0; i < 8000; i++) {
            int year = startYear + i;
            for (int j = 0; j < 12; j++) {
                int month = j + 1;
                int maxDays  = getMaxDays(year, month);
                for (int k = 0; k < maxDays; k++) {
                    int day = k + 1;
                     String dateString = year + " " + (month < 10 ? "0" + month : month) + "-" +
                             (day < 10 ? "0" + day : day) + " 00:00:00";
                     if(!dateString.equals(sdf.format(sdf.parse(dateString)))) {
                         System.out.println(dateString + "\t\t" + sdf.format(sdf.parse(dateString)));
                     }
                }
            }
        }
    }
}
