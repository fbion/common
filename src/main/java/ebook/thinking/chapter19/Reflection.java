package ebook.thinking.chapter19;

import ebook.thinking.chapter18.process.OSExcute;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/25.
 */
public class Reflection {
    public static Set<String> analyze(Class<?> enumClass)
    {
        System.out.println("-----Analyze " + enumClass + "-----");
        System.out.println("Interfaces: ");
        for (Type type : enumClass.getGenericInterfaces()) {
            System.out.println(type);
        }
        System.out.println("Base: " + enumClass.getSuperclass());
        System.out.println("Methods: ");
        Set<String> methods = new HashSet<>();
        for (Method method : enumClass.getMethods()) {
            methods.add(method.getName());
        }
        System.out.println(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        System.out.println("Explore.containALl(Enum)? " + exploreMethods.containsAll(enumMethods));
        System.out.print("Explore.removeALl(Enum): " + exploreMethods.removeAll(enumMethods));
        System.out.println(exploreMethods);
        OSExcute.command("javap F:\\idea-projects\\test\\out\\test\\test\\thinking\\chapter18\\Explore.class");
    }
}

enum Explore{
    HERE,THERE
}
