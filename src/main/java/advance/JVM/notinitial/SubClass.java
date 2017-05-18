package advance.JVM.notinitial;

/**
 * Created by Administrator on 2016/10/9.
 */
public class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init");
    }
}
