package test.order;

/**
 * description： <br>
 * createTime: 2018/3/1214:21 <br>
 *
 * @author zzh
 */
public class InstanceTest {
    static class A {
        int method () {
            return n;
        }
        int m = method();
        int n = 1;
    }
    static class B {
        int method () {
            return n;
        }
        int n = 1;
        int m = method();
    }
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.n);
        System.out.println(a.m);
        B b = new B();
        System.out.println(b.n);
        System.out.println(b.m);
    }
}
