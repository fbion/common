package advance.JVM.gc;

/**
 * 描述： <br>
 * 创建时间: 2017/8/2217:04 <br>
 *-XX:+PrintGCDetails
 * -verbose:gc
 * @author 周志辉
 */
public class TestSlot {

    public static void main(String[] args) {
        {
            {
                byte[] placeHolder = new byte[32 * 1024 * 1024];
            }
            System.gc();
            System.out.println();
        }
        System.gc();
        System.out.println();
        int a = 0;
        System.gc();
        System.out.println();
        {
            byte[] placeHolder = new byte[32 * 1024 * 1024];
            placeHolder = null;
        }
        System.gc();
    }
}
