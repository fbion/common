package test.base;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 2016/3/23.
 */
public class TestForName {
    static Unsafe getUnsafe(){
        Field f = null;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            return unsafe;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        Class cl = Class.forName("test.Color");
//        Color c = (Color)cl.newInstance();
//        System.out.println(c);

//        Constructor constructor = Color.class.getConstructor(new Class[]{});
//        System.out.println(constructor.newInstance());

        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
        Color c = (Color)unsafe.allocateInstance(Color.class);
        System.out.println(c);
        System.out.println(c.name() == null);
        System.out.println(c == null);
        System.out.println(c instanceof Color);
        System.out.println(Color.BLUE.name());

//        String str = null;
//        System.out.println(str instanceof String);
//        System.out.println();
    }
}

enum Color{
    RED,BLUE,YELLOW
}
