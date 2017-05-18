package thinking.chapter15.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/5.
 */
public class DynamicProxyMixin {
    public static void main(String[] args) {
        Object mixin= MixinProxy.newInstance(new TwoTuple(new BasicImpl(),Basic.class),
                new TwoTuple(new TimeStampedImpl(), TimeStamped.class),
                new TwoTuple(new SerialNumberedImpl(), SerialNumbered.class));
        Basic b = (Basic)mixin;
        TimeStamped t = (TimeStamped)mixin;
        SerialNumbered s = (SerialNumbered)mixin;
//        System.out.println(Arrays.deepToString(mixin.getClass().getMethods()));
        System.out.println(mixin);
        b.setValue("hello");
        System.out.println(b.getValue());
        System.out.println(t.getStamp());
        System.out.println(s.getSerialNumber());
    }
}

class TwoTuple<T, B> {
    T first;
    B second;
    public TwoTuple(T first, B second){
        this.first = first;
        this.second = second;
    }
}

class MixinProxy implements InvocationHandler {
    Map<String, Object> delegatsByMethod;

    public MixinProxy(TwoTuple<Object, Class<?>>... pairs) {
        delegatsByMethod = new HashMap<>();
        for (TwoTuple<Object, Class<?>> pair : pairs) {
            for (Method method : pair.second.getMethods()) {
                String methodName = method.getName();
                if (!delegatsByMethod.containsKey(methodName)) {
                    delegatsByMethod.put(methodName, pair.first);
                }
            }
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        String methodName = method.getName();
        Object delegate = delegatsByMethod.get(methodName);
        return method.invoke(delegate, args);
    }

    @SuppressWarnings("unchecked")
    public static Object newInstance(TwoTuple... pairs){
        Class[] interfaces = new Class[pairs.length];
        for (int i = 0; i < pairs.length; i++) {
            interfaces[i] = (Class)pairs[i].second;
        }
        ClassLoader cl = pairs[0].first.getClass().getClassLoader();
        return Proxy.newProxyInstance(cl, interfaces, new MixinProxy(pairs));
    }
}