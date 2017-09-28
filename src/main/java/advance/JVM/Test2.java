package advance.JVM;

/**
 * 描述： <br>
 * 创建时间: 2017/9/2117:22 <br>
 *
 * @author 周志辉
 */
public class Test2 {

    private int y;

    private B b;

    private int z;

    private int sum;

    static class B {

        int value;


        final int get() {
            return value;
        }
    }


    public void foo() {
        y = b.get();
        //……do stuff……
        z = b.get();
        sum = y + z;
    }
}
