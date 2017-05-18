package test.recent.test;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2016/1/28.
 */
public class TestReflectionOfGeneric<T>{
    T t;
    public TestReflectionOfGeneric(T t){
        this.t = t;
    }
    public Object getValueOfField(String fieldName) throws NoSuchFieldException , IllegalAccessException{
            Field f = t.getClass().getDeclaredField(fieldName);
        return f.get(this.t);
    }
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println(new TestReflectionOfGeneric(new Person("Zhangsan", 13)).getValueOfField("name"));
    }
}

class Person{
    protected int age;
    protected String name;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
}