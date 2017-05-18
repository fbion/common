package test;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/6/30.
 */
public class Test20 {
    public static void main(String[] args) {
        B b = new B();
        System.out.println();
        B b1 = new B();
    }
}
class A{
    static {
        System.out.println("A clinit");
    }
    {
        System.out.println("A init");
    }
    public A() {
        System.out.println("A constructor");
    }
}
class B extends A {
    static {
        System.out.println("B clinit");
    }
    {
        System.out.println("B init");
    }
    public B() {
        System.out.println("B constructor");
    }
}