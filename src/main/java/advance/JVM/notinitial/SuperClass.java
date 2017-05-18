package advance.JVM.notinitial;

/**
 * Created by Administrator on 2016/10/9.
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init.");
    }
    public static int value;
}
