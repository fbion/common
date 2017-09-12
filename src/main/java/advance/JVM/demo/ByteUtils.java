package advance.JVM.demo;

/**
 * 描述： bytes数组处理工具类<br>
 * 创建时间: 2017/9/1115:13 <br>
 *
 * @author 周志辉
 */
public class ByteUtils {

    public static int bytes2Int(byte[] bytes, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int n = ((int) bytes[i]) & 0xff;
            n <<= (--len) * 8;
            sum = n + sum;
        }
        return sum;
    }


    public static byte[] int2Bytes(int value, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;
    }


    public static String bytes2String(byte[] bytes, int start, int len) {
        return new String(bytes, start, len);
    }


    public static byte[] string2Bytes(String newStr) {
        return newStr.getBytes();
    }


    public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replaceBytes) {
        byte[]  newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
        System.arraycopy(originalBytes, 0, newBytes, 0, offset);
        System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
        System.arraycopy(originalBytes, offset + len, newBytes,
                offset + replaceBytes.length, originalBytes.length - offset - len);
        return newBytes;
    }
}
