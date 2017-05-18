package Algorithms.classic;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Classic {

    public static void printMultiplyTable() {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i*j + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 裴波那数列
     * @param maxTimes
     */
    public static void printFabonacci(int maxTimes) {
        int i1 = 0;
        int i2 = 1;
        for (int i = 0; i < maxTimes; i++) {
            if(i % 2 == 0) {
                i1 += i2;
                System.out.println(i2);
            } else {
                i2 += i1;
                System.out.println(i1);
            }
        }
    }

    public static void printTriangle(int times) {
        for (int i = 0; i < times; i++) {
            for (int j = times - i; j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void printTriangleNumber(int maxLine) {
        for (int i = 0; i < maxLine; i++) {
            for (int j = maxLine - i; j > 0; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i ; j++) {
                System.out.print(j + 1);
            }
            for (int j = i - 1; j >= 0 ; j--) {
                System.out.print(j + 1);
            }
            System.out.println();
        }
    }

    public static int calculateSum(int val) {
        List<String> list = Arrays.asList((val + "").split(""));
        int sum = list.stream().mapToInt((element) ->
            Integer.parseInt(element)
        ).sum();
        return sum;
    }


    public static int findMaxCommonDivisor(int val1, int val2) {
        int temp1 = val1;
        int temp2 = val2;
        int left;
        while((left = temp1 % temp2) != 0) {
            temp1 = temp2;
            temp2 = left;
        }
        return temp2;
    }

    public static long findMaxCommonDivisor(long val1, long val2) {
        long temp1 = val1;
        long temp2 = val2;
        long left;
        while((left = temp1 % temp2) != 0) {
            temp1 = temp2;
            temp2 = left;
        }
        return temp2;
    }

    public static Date fixToZero(Date date) {
        long timestamp = date.getTime();
        long newTime = ((timestamp + 28800000) / 86400000) * 86400000 - 28800000;
        return new Date(newTime);
    }

    public static void main(String[] args) {
//        int[] array ={123, 12345, 1234567};
//        for (int i : array) {
//            System.out.print(String.format("%10s", i + "\t\t\t"));
//            System.out.println(calculateSum(i));
//        }

        Calendar c1 = Calendar.getInstance();
        System.out.println(new Date(c1.getTimeInMillis()));
        System.out.println(fixToZero(new Date(c1.getTimeInMillis())));
    }
}
