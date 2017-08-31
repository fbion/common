package advance.JVM;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述： <br>
 * 创建时间: 2017/8/3017:59 <br>
 *
 * @author 周志辉
 */
public class MethodTest {
    static class ClassA{
        public void println(Object obj) {
            System.out.println("ClassA println");
            System.out.println(obj);
        }
    }


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        Method m = obj.getClass().getMethod("println", new Class[]{Object.class});
        m.invoke(obj, "alsdkfjklsf");
    }
}
