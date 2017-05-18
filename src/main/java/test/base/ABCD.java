package test.base;

/**
 * Created by zzh on 2016-03-10.
 */
public class ABCD {
    public static void main(String[] args) {
        A a1 =new A();
        A a2 =new B();
        B b = new B();
        C c = new C();
        D d = new D();
        System.out.println(a1.show(b));
        System.out.println(a1.show(c));
        System.out.println(a1.show(d));

        System.out.println(a2.show(b));
        System.out.println(a2.show(c));
        System.out.println(a2.show(d));

        System.out.println(b.show(b));
        System.out.println(b.show(c));
        System.out.println(b.show(d));
    }
}
class A{
    public String show(D d){
        return "D";
    }
    public String show(A d){
        return "AA";
    }
}
class B extends A{
    public String show(B b){
        return "B";
    }
    public String show(A d){
        return "BA";
    }
}
class C extends B{
    public String show(C b){
            return "C";
    }
    public String show(A d){
        return "CA";
    }
}
class D extends C{
    public String show(D b){
        return "D";
    }
    public String show(A d){
        return "DA";
    }
}