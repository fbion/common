package test.performancetest;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/5/24.
 */
public class TestStringLength {
    public static void main(String[] args) {
        String str = "1";
        try{
            for (int i = 0;i< 32; i++) {
                str += str;
                System.out.println("length : " + str.length());
                System.gc();
                TimeUnit.SECONDS.sleep(1);
            }
            System.out.println("out of loop.");
            str += "1";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
