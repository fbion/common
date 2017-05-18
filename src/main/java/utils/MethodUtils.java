package utils;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/7/28.
 */
public class MethodUtils {
    private MethodUtils(){}

    public static Class findMethodDeclaredClass(Class clazz, String methodName) {
        if(methodName == null) {
            return null;
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if(methodName.equals(method.getName())) {
                return clazz;
            }
        }
        if(clazz.getSuperclass() == null) {
            return null;
        } else {
            return findMethodDeclaredClass(clazz.getSuperclass(), methodName);
        }
    }

    public static void main(String[] args) {
        System.out.println(findMethodDeclaredClass(System.in.getClass(), "read"));
    }
}
