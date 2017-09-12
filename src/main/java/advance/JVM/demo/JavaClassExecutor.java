package advance.JVM.demo;

import java.lang.reflect.Method;

/**
 * 描述：JavaClass执行工具 <br>
 * 创建时间: 2017/9/1216:13 <br>
 *
 * @author 周志辉
 */
public class JavaClassExecutor {

    public static String execute(byte[] classBytes) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classBytes);
        byte[] modifyBbytes = cm.modiryUTF8Constant("java/lang/System",
                "advance/JVM/demo/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadBytes(modifyBbytes);
        try {
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}
