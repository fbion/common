package advance.JVM.notinitial;

/**
 * Created by Administrator on 2016/10/9.
 */
public class NotInitialization {
    public static void test1() {
        System.out.println(SubClass.value);
    }

    public static void test2() {
        SuperClass[] array = new SuperClass[10];
    }
    //-XX:+TraceClassLoading
    public static void main(String[] args) {
        test2();
    }
}
