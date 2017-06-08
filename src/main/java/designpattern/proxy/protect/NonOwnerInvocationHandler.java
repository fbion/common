package designpattern.proxy.protect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述： <br>
 * 创建时间: 2017/6/114:11 <br>
 *
 * @author 周志辉
 */
public class NonOwnerInvocationHandler implements InvocationHandler {
    PersonBean person;


    public NonOwnerInvocationHandler(PersonBean person) {
        this.person = person;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try {
            if(method.getName().startsWith("get")) {
                return method.invoke(person, args);
            } else if(method.getName().equals("setHotOrNotRating")) {
                return method.invoke(person, args);
            } else if(method.getName().startsWith("set")) {
                throw new IllegalAccessException();
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
