package test.base;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/7/23.
 */
public class TestFinal {
    private final int i = 10;
    private final Object o = new Object();
    public int getI() {
        return i;
    }
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        TestFinal test = new TestFinal();
        System.out.println(test.i);
        System.out.println(test.o);

        Field f = test.getClass().getDeclaredField("i");
        f.setAccessible(true);
        f.setInt(test, 2);
        System.out.println(test.i);
        System.out.println(test.getI());
        int result = f.getInt(test);
        System.out.println(result);

        Field f2 = test.getClass().getDeclaredField("o");
        f2.setAccessible(true);
        f2.set(test, new Object());
        System.out.println(test.o);
        Object result2 =  f2.get(test);
        System.out.println(result2);
    }
}
