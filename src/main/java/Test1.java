import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.ExecutionException;

/**
 * description： <br>
 * createTime: 2018/1/2311:01 <br>
 *
 * @author zzh
 */
public class Test1 {
    static final String str = "good";


    public static String getStr() {
        return str;
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException, NoSuchFieldException, IllegalAccessException {
        Field f = Test1.class.getDeclaredField("str");
        f.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true); //Field 的 modifiers 是私有的
        modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
        String str1 = (String) f.get(null);
        str1 = str1.replace("good", "hello world");
        f.set(null, str1);
        System.out.println(str);
        System.out.println(getStr());
        System.out.println(f.get(null));
//        System.out.println(Test5.str);
//        System.out.println(Test5.getStr());
    }
}

class Test5 {
    public static final String str = "good";
    static {
        System.out.println("load Test5");
    }

    public static String getStr() {
        return str;
    }
}
