package test.puzzled;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/5/21.
 */
public class TestOrderOfConvertAndBoxing {
    public static void main(String[] args) {
        print(3);
    }

    public static void print(double d) {
        System.out.println("double");
    }

    public static void print(Double d) {
        System.out.println("Double");
    }

    public static void print(Integer i) {
        System.out.println("Integer");
    }

    public static void print(int i) {
        System.out.println("int");
    }
}
