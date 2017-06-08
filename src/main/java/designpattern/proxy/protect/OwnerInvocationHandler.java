package designpattern.proxy.protect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 描述： <br>
 * 创建时间: 2017/6/111:57 <br>
 *
 * @author 周志辉
 */
public class OwnerInvocationHandler implements InvocationHandler {
    PersonBean person;


    public OwnerInvocationHandler(PersonBean person) {
        this.person = person;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws IllegalAccessException {
        try {
            if(method.getName().startsWith("get")) {
                return method.invoke(person, args);
            } else if(method.getName().equals("setHotOrNotRating")) {
                throw new IllegalAccessException();
            } else if(method.getName().startsWith("set")) {
                return method.invoke(person, args);
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
