package test.base;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
@SuppressWarnings({"rawtypes","unchecked"})
public class TestConstrction<T>//加一个泛型用以代表将传进来的实体Bean类
{
    public void test(Class t){
        Constructor<T> con;
        T f1 = null;
        try
        {
            con = t.getConstructor(String.class);
            f1 = con.newInstance("hello world");
        }
        catch(NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
        System.out.println(f1);
    }
    public static void main(String[] args) throws ClassNotFoundException
    {
       new TestConstrction().test(Class.forName("test.base.F"));
       new TestConstrction().test(Class.forName("test.base.G"));
    }
}
class G {
    public G(){}
    public G(String str){
        System.out.println("G:" + str);
    }
}
class F {
    public F(){}
    public F(String str){
        System.out.println("F:" + str);
    }
}
