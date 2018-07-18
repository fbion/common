package test.innerouter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/8/6.
 */
public class Test12 {
    class Test13{}
    public static void main(String[] args) throws NoSuchMethodException {
        int i = 100;
        Runnable runnable = new Runnable(){
            public void run() {
                System.out.println(i);
            }
        };
        Class clazz = runnable.getClass();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName() + " isSynthetic: " + field.isSynthetic());
        }
        System.out.println(clazz.getName() + " isSynthetic: " + clazz.isSynthetic());
        Constructor[] constructors = clazz.getConstructors();
        System.out.println("constructors length : " + constructors.length);
        for (Constructor constructor : constructors) {
            System.out.println(constructor.getName() + " isSynthetic: " + constructor.isSynthetic());
        }

        Constructor[] constructors2 = Test12.class.getConstructors();
        System.out.println("constructors length : " + constructors2.length);
        for (Constructor constructor : constructors2) {
            System.out.println(constructor.getName() + " isSynthetic: " + constructor.isSynthetic());
        }

        Constructor<Test13> constructor3 = Test13.class.getDeclaredConstructor(new Class[]{Test12.class});
        System.out.println("Test13 class Name : " + Test13.class.getName());
        System.out.println("constructors length : " + constructor3);
        System.out.println(constructor3.getName() + " isSynthetic: " + constructor3.isSynthetic());
    }
}
