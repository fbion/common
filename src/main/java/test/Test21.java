package test;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * Created by Administrator on 2016/9/19.
 */
public class Test21 {
    private class CCCC{}
    protected class DDDD{}
    public static void test(Class clazz) {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            System.out.println(constructor);
        }
    }

    public static void test1() {
        test(Test21.class);
        System.out.println("------------");
        test(AAAA.class);
        System.out.println("------------");
        test(CCCC.class);
        System.out.println("------------");
        test(DDDD.class);
    }

    public static void main(String[] args) {
        Map map = new HashMap<>();
        Set set = new HashSet<>();
        List list = new ArrayList<>();
        System.out.println("map: ");
        try {
            map.put(null, 1);
            map.put(null, null);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        System.out.println(map);
        System.out.println("set: ");
        try {
            set.add(1);
            set.add(null);
            set.add(2);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        System.out.println(set);
        System.out.println("list: ");
        try {
            list.add(null);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        System.out.println(list);
    }
}
class AAAA{}