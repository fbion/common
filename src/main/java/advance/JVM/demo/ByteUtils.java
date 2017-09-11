package advance.JVM.demo;

/**
 * 描述： bytes数组处理工具类<br>
 * 创建时间: 2017/9/1115:13 <br>
 *
 * @author 周志辉
 */
public class ByteUtils {

    public static int bytes2Int(byte[] classBytes, int offset, int u1) {

        return 0;
    }


    public static String bytes2String(byte[] bytes, int start, int len) {
        return new String(bytes, start, len);
    }


    public static byte[] string2Bytes(String newStr) {
        return newStr.getBytes();
    }


    public static byte[] int2Bytes(int length, int u2) {

        return null;
    }


    public static byte[] bytesReplace(byte[] classBytes, int i, int u2, byte[] strLen) {

        return null;
    }
}
