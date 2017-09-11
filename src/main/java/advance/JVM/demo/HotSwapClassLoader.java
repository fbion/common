package advance.JVM.demo;

/**
 * 描述： <br>
 * 创建时间: 2017/9/1114:57 <br>
 *
 * @author 周志辉
 */
public class HotSwapClassLoader extends ClassLoader {

    public HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class loadBytes(byte[] classBytes) {
        return defineClass(null, classBytes, 0, classBytes.length);
    }
}
