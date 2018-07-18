package test.base;

/**
 * description: <br>
 * createTime: 2017/11/2016:45 <br>
 *
 * @author zzh
 */
public class TestIntClass {

    public static void main(String[] args) {
        System.out.println(int.class);
        System.out.println(Integer.TYPE);
        System.out.println(int.class == Integer.TYPE);
        System.out.println(int.class instanceof Class);
        System.out.println(int.class == Integer.class);
    }
}
