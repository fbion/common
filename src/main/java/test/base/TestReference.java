package test.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class TestReference
{
    public static Map<String, Object> map = new HashMap<String, Object>();

    @SuppressWarnings({"rawtypes"})
    public static void main(String[] args)
    {
        map.put("1", 1);
        //创建一个弱引用,指向map所指向的对象,通过get()方法可以取到map对象,弱引用不会对垃圾回收产生影响,
        Reference re = new WeakReference<Map>(map);
        
        //map置空,使原来指向的map对象变为不可达对象
        map = null;
        System.out.println(re.get());
        
        //调用gc方法,通常调用了也不能保证JVM就一定会回收垃圾
        System.gc();
        
        //回收后,通过re就取不到对象了,说明原来的Map对象已经被回收了
        System.out.println(re.get());
    }
}
