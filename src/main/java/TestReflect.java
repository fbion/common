import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * description: <br>
 * createTime: 2018/5/1011:16 <br>
 *
 * @author zzh
 */
public class TestReflect {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = PrivateMethod.class.getDeclaredMethod("test");
        m.setAccessible(true);
        m.invoke(new PrivateMethod(), new Object[]{});

        Method method2 = PrivateMethod.class.getMethod("test");

        Method[] methods = PrivateMethod.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
}

class PrivateMethod{
    private void test() {
        System.out.println("hello world");
    }
}