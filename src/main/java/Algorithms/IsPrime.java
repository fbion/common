package Algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class IsPrime {
    /**
     * 试除法
     * 2，为第一个质数，添加到结果集，然后对从3开始的所有奇数进行试除
     * 从2试除到测试数的平方根，只要有一个整除，则不为质数
     * 从结果集存取次数相对少，使用集合类影响不大
     * @param val
     */
    public static void test1(int val) {
        int[] result = new int[val/2];
        int size = 0;
        result[size++] = 2;
        int total = 0;
        for (int i = 3; i <= val; i += 2) {
            boolean flag = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                total++;
                if(i % j ==0 && i != j) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                result[size++] = i;
            }
        }
        System.out.println("\t\ttotal loop times : " + total);
        System.out.println("\t\tresult size : " + size);
    }
    /**
     * 改进试除法
     * 2，为第一个质数，添加到结果集，然后对从3开始的所有奇数进行试除
     * 从结果集中依次取出已添加的小于测试数的平方根的结果进行试除，只有有一个整除，则不是质数
     * 每次从结果集中取出数据试除，频繁从容器中取数据，导致当使用集合类为容器时，包装拆装导致效率大幅下降，所以改用数组保存
     * @param val
     */
    public static void test2(int val) {
        int[] result = new int[val/2];
        int size = 0;
        result[size++] = 2;
        int total = 0;
        for (int i = 3; i < val; i += 2) {
            boolean flag = true;
            for (int j = 0; j < size; j++) {
                total++;
                if(i % result[j] == 0) {
                    flag = false;
                    break;
                } else if(result[j] > Math.sqrt(i)) {
                    break;
                }
            }
            if(flag) {
                result[size++] = i;
            }
        }
        System.out.println("\t\ttotal loop times : " + total);
        System.out.println("\t\tresult size : " + size);
    }
    /**
     * 筛选法
     * 初始化一个val + 1大小的boolean数组，则初始化均为false
     * 从序号为2的开始，到val的平方根，然后从2开始倍乘，把结果做为下标，置对应数组元素为true，表明该下标数不是质数
     * 以空间换时间，当计算规模小时，循环次数多，效率低，规模大时效率明显提高，
     * @param val
     */
    public static void test3(int val) {
        List<Integer> list = new ArrayList<>();
        int total = 0;
        boolean[] array = new boolean[val + 1];
        for (int i = 2; i < Math.sqrt(val); i++) {
            for (int j = 2; j * i < val; j++) {
                total++;
                array[j * i] = true;
            }
        }
        for (int i = 2; i < val; i++) {
            total++;
            if(!array[i]) {
                list.add(i);
            }
        }
        System.out.println("\t\ttotal loop times : " + total);
        System.out.println("\t\tresult size : " + list.size());
    }
    public static void main(String[] args) throws InterruptedException {
        int[] array = new int[]{ 1000000 };//100, 10000,
        long start;
        for (int i : array) {
            System.out.println("Max value : " + i);
            start = System.currentTimeMillis();
            test1(i);
            System.out.println("\t\t\t\t\t\ttest1 time : " + (System.currentTimeMillis() - start));

            start = System.currentTimeMillis();
            test2(i);
            System.out.println("\t\t\t\t\t\ttest2 time : " + (System.currentTimeMillis() - start));

            start = System.currentTimeMillis();
            test3(i);
            System.out.println("\t\t\t\t\t\ttest3 time : " + (System.currentTimeMillis() - start));
        }
    }
}
