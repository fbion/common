package Algorithms.classic;

import java.text.ParseException;

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
    public static void main(String[] args) throws ParseException {
        test();
    }
}
