package Algorithms.recursion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.ExecutionException;

public class CharCombination
{
    /**
     * 根据提供的字符集组成的数组，找出所有指定长度以内的组合字符串
     * 递归实现
     * @param resultList
     * @param src
     * @param maxLength
     * @param minLength
     * @param des
     * @return
     */
    public static List<String> charCombination(List<String> resultList, String[] src, int maxLength, int minLength, String des) {
        if(maxLength < minLength || minLength < 0) {
            System.exit(1);
        }
        for (String str : src) {
            if (maxLength == 1) {
                resultList.add(des + str);
                System.out.println(des + str);
            }
            else if(minLength - 1 > 0) {
                charCombination(resultList, src, maxLength - 1, minLength - 1, des + str);
            }
            else {
                charCombination(resultList, src, maxLength - 1, minLength, des + str);
                resultList.add(des + str);
                System.out.println(des + str);
            }
        }
        return resultList;
    }

    /**
     * 根据提供的字符集组成的数组，找出所有指定长度以内的组合字符串
     * 非递归实现
     * @param resultList
     * @param src
     * @param maxLength
     * @param minLength
     * @param des
     * @return
     */
    public static List<String> charCombinationNoneRecursion(List<String> resultList,String[] src, int maxLength, int minLength, String des) {
        Stack stack = new Stack();
        int index = 0;
        while(index < src.length) {
            for (; index < src.length; index++) {
                if (maxLength == 1) {
                    resultList.add(des + src[index]);
//                    System.out.println(des + src[index]);
                } else if(minLength - 1 > 0) {
                    stack.push(index);
                    stack.push(maxLength);
                    stack.push(minLength);
                    stack.push(des);
                    maxLength = maxLength - 1;
                    minLength = minLength - 1;
                    des = des + src[index];
                    index = -1;
                } else {
                    resultList.add(des + src[index]);
//                    System.out.println(des + src[index]);
                    stack.push(index);
                    stack.push(maxLength);
                    stack.push(minLength);
                    stack.push(des);
                    maxLength = maxLength - 1;
                    des = des + src[index];
                    index = -1;
                }
            }
            while(index == src.length && !stack.empty()) {
                des = (String)stack.pop();
                minLength = (Integer)stack.pop();
                maxLength = (Integer)stack.pop();
                index = (Integer)stack.pop();
                index++;
            }
        }
        return resultList;
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        String[] strs = new String[] { "a", "b", "c", "d" , "e"};
        List<String> resultList = new ArrayList<>();
        System.out.println(charCombination(resultList, strs, 5, 0,  "") + "\ntotal size : " + resultList.size());

        resultList.clear();
        System.out.println(charCombinationNoneRecursion(resultList, strs, 5, 0,  "") + "\ntotal size : " + resultList.size());
    }
}
