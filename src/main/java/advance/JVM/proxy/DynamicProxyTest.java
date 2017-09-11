package advance.JVM.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.lang.reflect.Proxy;


/**
 * 描述： <br>
 * 创建时间: 2017/9/817:46 <br>
 *
 * @author 周志辉
 */
public class DynamicProxyTest {

    interface IHello{
        void sayHello();
    }

    static class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    static class DynamicProxy implements InvocationHandler {
        Object originalObject;

        Object bind(Object obj) {
            this.originalObject = obj;
            return Proxy.newProxyInstance(originalObject.getClass().getClassLoader(), originalObject.getClass()
                    .getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Welcome!");
            return method.invoke(originalObject, args);
        }
    }


    public static void main(String[] args) {
        System.getProperties().put("", "true");
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
    }
}
