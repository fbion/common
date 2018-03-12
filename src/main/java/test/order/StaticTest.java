package test.order;

/**
 * description： <br>
 * createTime: 2018/3/511:33 <br>
 *
 * @author zzh
 */
public class StaticTest {
    static class A {
        static {
            i = 2;
        }
        public static int i = 1;
    }
    static class B extends A{
        public static int a = i;
    }
    static class C {
        public static int i = 1;
        static {
            i = 2;
        }
    }
    static class D extends C {
        public static int a = i;
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println(B.a);
        System.out.println(D.a);
    }
}
