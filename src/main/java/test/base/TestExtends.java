package test.base;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/3/29.
 */
public class TestExtends {
    public static void main(String[] args) {
        SubClass1 subClass1 = new SubClass1();
        Interface i1 = new SubClass1();
        System.out.print("i1 instanceof Interface : \t\t\t");
        System.out.println(i1 instanceof Interface);
        System.out.print("subClass1 instanceof Interface : \t");
        System.out.println(subClass1 instanceof Interface);
        System.out.println("SubClass1.class.getInterfaces : \t" + Arrays.deepToString(SubClass1.class.getInterfaces()));
        SubClass2 subClass2 = new SubClass2();
        Interface i2 = new SubClass2();
        System.out.print("i2 instanceof Interface : \t\t\t");
        System.out.println(i2 instanceof Interface);
        System.out.print("subClass2 instanceof Interface : \t");
        System.out.println(subClass2 instanceof Interface);
        System.out.println("SubClass2.class.getInterfaces : \t" + Arrays.deepToString(SubClass2.class.getInterfaces()));
    }
}
class SubClass1 extends SuperClass1 {}
class SuperClass1 implements Interface {}

class SubClass2 extends SuperClass2 implements Interface {}
class SuperClass2 implements Interface {}
interface Interface {}