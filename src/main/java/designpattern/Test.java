package designpattern;

/**
 * Created by zzh on 2015-12-07.
 */
public class Test {
    public static void test(Interface inf) {
        System.out.println("In test");
    }

    public static void main(String[] args) {
//        Impl inf = new Impl();
//        test(inf);
    }
}

interface Interface{}

class Impl implements Interface {}