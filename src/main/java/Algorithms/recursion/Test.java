package Algorithms.recursion;

import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class Test {
    public static void test() {
        String str[] = { "A", "B", "C", "D", "E" };
        int nCnt = str.length;
        int nBit = 1<<nCnt;
        int count = 0;
        int total = 0;
        for (int i = 1; i <= nBit; i++) {
            for (int j = 0; j < nCnt; j++) {
                if ((1<<j & i ) == 0) {
                    System.out.print(str[j]);
                }
                count++;
            }
            System.out.println("");
            total++;
        }
        System.out.println("total:" + count);
        System.out.println("size : " + total);
    }

    public static List<String> charCombinationNoneRecursion1(List<String> resultList, String[] str, int maxLength, int minLength, String des) {
        int nCnt = str.length;

        int nBit = 1<<nCnt;

        int count = 0;
        for (int i = 1; i <= nBit; i++) {
            String temp = "";
            for (int j = 0; j < nCnt; j++) {
                if ((1<<j & i) != 0) {
                    temp += str[j];
                    count++;
                }
            }
            resultList.add(temp);

        }
        System.out.println("total:" + count);
        return resultList;
    }

    public static void main(String[] args) {
//        String str[] = { "A", "B", "C", "D", "E" };
//        List<String> resultList = new ArrayList<>();
//        System.out.println(charCombinationNoneRecursion1(resultList, str, 4, 0, ""));
//        System.out.println("total size : " + resultList.size());

        System.out.println((1<<5));
        test();
    }
}
