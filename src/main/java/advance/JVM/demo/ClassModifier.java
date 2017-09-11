package advance.JVM.demo;

/**
 * 描述： 修改Class文件，暂时只提供修改常量池常量的功能<br>
 * 创建时间: 2017/9/1115:02 <br>
 *
 * @author 周志辉
 */
public class ClassModifier {

    /**
     * Class文件中常量池起始偏移
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /**
     * CONSTANT_Utf8_info常量的tag标志
     */
    private static final int CONSTANT_utf8_info = 1;

    /**
     * 常量池中11种常量所占的长度，CONSTANT_Utf8_info型常量除外，因为它不是定长的
     */
    private static final int[] CONSTANT_ITEM_LENGTH= {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};

    private static final int u1 = 1;

    private static final int u2 = 2;

    private byte[] classBytes;


    public ClassModifier(byte[] classBytes) {
        this.classBytes = classBytes;
    }


}
