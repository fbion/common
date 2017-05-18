package test;


import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Administrator on 2016/10/10.
 */
public class Test23 {
    public static void test1() throws NoSuchFieldException, IllegalAccessException  {
        long l = System.currentTimeMillis();
        String str1 = new String("abc");
        String str2 = new String("abc");
        String str3 = l + "";
        String str4 = l + "";
        Field f = String.class.getDeclaredField("value");
        f.setAccessible(true);
        char[] chars1 = (char[])f.get(str1);
        char[] chars2 = (char[])f.get(str2);
        char[] chars3 = (char[])f.get(str3);
        char[] chars4 = (char[])f.get(str4);
        chars1[1] = 'e';
        chars3[0] = '9';
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
    }

    public class InnerClass{}

    public static void test2() {
        Set<MyTest> set = new HashSet<>();
        MyTest test1 = new MyTest(1);
        MyTest test2 = new MyTest(1);
        set.add(test1);
        System.out.println(set);
        set.add(test2);
        System.out.println(set);

        TreeSet<SuperClass> set2 = new TreeSet<>();
        set2.add(new SuperClass());
        System.out.println();
        set2.add(new SubClass());
        set2.add(new SuperClass());
    }

    /**
     * -enableassertions或-ea开启断言
     * @param args
     */
    public static void main(String[] args){
        int i = 0;
        for(i=0;i<5;i++)
        {
            System.out.println(i);
        }
//假设程序不小心多了一句--i;
//        --i;
        assert i == 5;
        System.out.println(i);
    }
}

class SuperClass implements Comparable<SuperClass> {
    @Override
    public int compareTo(SuperClass o) {
        System.out.println("SuperClass");
        return 0;
    }
}

class SubClass extends SuperClass{
    @Override
    public int compareTo(SuperClass o) {
        System.out.println("SubClass");
        return 0;
    }
}

class MyTest {
    private int i;

    public MyTest(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MyTest) {
            return obj.hashCode() == hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return i % 2;
    }

    @Override
    public String toString() {
        return "MyTest{" +
                "i=" + i +
                '}';
    }
}