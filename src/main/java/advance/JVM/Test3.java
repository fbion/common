package advance.JVM;

/**
 * 描述： <br>
 * 创建时间: 2017/9/2217:00 <br>
 * foo方法内联编译到testInline方法，就会发现obj是null，if里的代码是无效代码，不会执行，但不内联编译则无法发现
 *
 * @author 周志辉
 */
public class Test3 {

    public static void foo(Object obj) {
        if (obj != null) {
            System.out.println("do something");
        }
    }


    public static void testInline(String[] args) {
        Object obj = null;
        foo(obj);
    }


    public static void main(String[] args) throws NoSuchMethodException {
        for (String s : "8605|8600".split("\\|")) {
            System.out.println(s);
        }
    }
}
