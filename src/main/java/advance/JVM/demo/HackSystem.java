package advance.JVM.demo;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.channels.Channel;
import java.util.Properties;

/**
 * 描述： <br>
 * 创建时间: 2017/9/1211:58 <br>
 *
 * @author 周志辉
 */
public class HackSystem {

    public static final InputStream in = System.in;

    private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    public static final PrintStream out = new PrintStream(buffer);

    public static final PrintStream error = out;

    public static String getBufferString() {
        return buffer.toString();
    }

    public static void clearBuffer() {
        buffer.reset();
    }

    public static void setSecurityManager(final SecurityManager sm) {
        System.setSecurityManager(sm);
    }

    public static SecurityManager getSecurityManager() {
        return System.getSecurityManager();
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    public static int identityHashCode(Object x) {
        return System.identityHashCode(x);
    }

    public static String clearProperty(String key) {
        return System.clearProperty(key);
    }

    public static Console console() {
        return System.console();
    }

    public static void exit(int status) {
        System.exit(status);
    }

    public static void gc() {
        System.gc();
    }

    public static java.util.Map<String,String> getenv() {
        return System.getenv();
    }

    public static String getenv(String name) {
        return System.getenv(name);
    }

    public static Properties getProperties() {
        return System.getProperties();
    }

    public static String getProperty(String key) {
        return System.getProperty(key);
    }

    public static String getProperty(String key, String def) {
        return System.getProperty(key, def);
    }

    public static Channel inheritedChannel() throws IOException {
        return System.inheritedChannel();
    }

    public static String lineSeparator() {
        return System.lineSeparator();
    }

    public static void load(String filename) {
        System.load(filename);
    }

    public static void loadLibrary(String libname) {
        System.loadLibrary(libname);
    }

    public static String mapLibraryName(String libname) {
        return System.mapLibraryName(libname);
    }

    public static long nanoTime() {
        return System.nanoTime();
    };

    public static void runFinalization() {
        System.runFinalization();
    }

    public static void runFinalizersOnExit(boolean value) {
        System.runFinalizersOnExit(value);
    }

    public static void setErr(PrintStream err) {
        System.setErr(err);
    }

    public static void setIn(InputStream in) {
        System.setIn(in);
    }

    public static void setOut(PrintStream out) {
        System.setOut(out);
    }

    public static void setProperties(Properties props) {
        System.setProperties(props);
    }

    public static String setProperty(String key, String value) {
        return System.setProperty(key, value);
    }
}
