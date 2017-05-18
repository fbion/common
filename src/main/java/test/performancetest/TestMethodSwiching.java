package test.performancetest;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/16.
 */
public class TestMethodSwiching {
    static void test1(){
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 10; k++) {
                    function1(i, j, k);
                }
            }
        }
    }
    static void function1(int i, int j, int k){
        System.out.println("Hello World!");
    }
    static void test2(){
        function2();
    }
    static void function2(){
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 10; k++) {
                    System.out.println("Hello World!");
                }
            }
        }
    }
    public static void main(String[] args) {
        long start = System.nanoTime();
        test1();
        long duration1 = System.nanoTime() - start;
        start = System.nanoTime();
        test2();
        long duration2 = System.nanoTime() - start;
        System.out.println("test1 : " + TimeUnit.SECONDS.convert(duration1, TimeUnit.NANOSECONDS) + "ms");
        System.out.println("test2 : " + TimeUnit.SECONDS.convert(duration2, TimeUnit.NANOSECONDS) + "ms");
    }
}
