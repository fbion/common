package test;


import sun.misc.Unsafe;
import test.utils.GetUnsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Administrator on 206/0/8.
 */
public class Q1 {
    public static void main(String args[]) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        System.out.println(Singleton.getInstance());
        Class clazz = Singleton.class;
        Constructor constructor = clazz.getDeclaredConstructor(new Class[]{});
        constructor.setAccessible(true);
        Singleton temp = (Singleton) constructor.newInstance();
        System.out.println(temp);
        Field f = clazz.getDeclaredField("instance");
//        f.setAccessible(true);
//        f.set(null, temp);

        Unsafe unsafe = GetUnsafe.getUnsafe();
        long l = unsafe.fieldOffset(f);
        unsafe.getAndSetObject(null, l, temp);
        System.out.println(Singleton.getInstance());
    }
}

class Singleton{
    private static Singleton instance = new Singleton();
    private Singleton(){}
    public static Singleton getInstance() {
        return instance;
    }
}