import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * description： <br>
 * createTime: 2018/1/816:08 <br>
 *
 * @author zzh
 */
public class TestLoop {

    public static void main(String[] args) {
        TestE<String> testE1 = new TestE<>(String.class, 2);
        String[] array1 = testE1.getItems();
        array1[0] = "123";
        System.out.println(array1.getClass());
        System.out.println(Arrays.deepToString(array1));
    }
}
class TestE<E> {
    private final E[] items;
    public TestE(Class<E> e, int length) {
        this.items = (E[]) Array.newInstance(e, length);
    }


    public E[] getItems() {
        return items;
    }
}