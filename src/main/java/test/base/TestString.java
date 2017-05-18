package test.base;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/4/19.
 */
public class TestString {
    public static int find(String src, String des) {
        if(des.length() == 0) {
            System.out.println("The destination should not be empty string");
            return -1;
        }
        if(des.length() > src.length()){
            System.out.println("Not found");
            return -1;
        }
        char[] chars1 = src.toCharArray();
        char[] chars2 = des.toCharArray();
        for (int i = 0; i < chars1.length - chars2.length; i++) {
            if(chars2[0] == chars1[i]){
                for (int j = 1; j < chars2.length; j++) {
                    if(chars2[j] != chars1[i + j]){
                        break;
                    }else if(j == chars2.length -1 ){
                        System.out.println("Found out!the first index is " + (i + 1));
                        return i + 1;
                    }
                }
            }
        }
        System.out.println("Not found");
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(find("Hello", "He"));
        System.out.println(find("123Hello", "He"));
        System.out.println(find("123Hello", "He1"));
    }
}
