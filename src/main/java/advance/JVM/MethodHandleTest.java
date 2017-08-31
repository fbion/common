package advance.JVM;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * 描述： <br>
 * 创建时间: 2017/8/3017:52 <br>
 *
 * @author 周志辉
 */
public class MethodHandleTest {
    static class ClassA{
        public void println(String s) {
            System.out.println("ClassA println\t" + s);
        }
    }
    public static void main(String[] args) throws Throwable {
        Object obj1 = System.out;
        Object obj2 = new ClassA();
        getPrintlnMH(obj1).invokeExact("ajsldkfjslkdf");

        Method m1 = obj1.getClass().getMethod("println", new Class[]{String.class});
        m1.invoke(obj1, "alsdkfjklsf");

        System.out.println();
        getPrintlnMH(obj2).invokeExact("ajsldkfjslkdf");

        Method m2 = obj2.getClass().getMethod("println", new Class[]{String.class});
        m2.invoke(obj2, "alsdkfjklsf");
    }
    private static MethodHandle getPrintlnMH(Object receiver) throws NoSuchMethodException, IllegalAccessException {
        MethodType mt = MethodType.methodType(void.class, String.class);
        return MethodHandles.lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
    }
}
