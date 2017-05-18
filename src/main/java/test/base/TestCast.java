package test.base;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2016/7/15.
 */
public class TestCast {
    public static Boolean test() {
        return true;
    }
    public static String test1() {
        return "123";
    }
    public static Object test2() {
        return "123";
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        System.out.println(test1().length());
        System.out.println(test2());
        Class<String> clazz = String.class;
        Object o = "123";
        System.out.println(o.getClass());
        String str = clazz.cast(o);
        String str1 = clazz.newInstance();
        System.out.println(str1.length());
        System.out.println(str);
    }
}