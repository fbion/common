package advance.JVM.gc;

/**
 * Created by Administrator on 2016/5/31.
 * VM Args:-XX:+PrintGCDetails
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version", "not specified"));
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;
        System.gc();
    }
}
