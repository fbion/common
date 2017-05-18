package test;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/5.
 */
public class Test11 {
    public static void test(long l) {
        l = 123123123123123l;
        byte[] bytes = (l + "").getBytes();
        long result = Long.parseLong(new String(bytes));
        System.out.println(result);
        System.out.println(result == l);
    }
    public static void main(String[] args) {
        System.out.println(new Date(1471603508000l));
    }
}
