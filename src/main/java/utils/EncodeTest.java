package utils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 描述： <br>
 * 创建时间: 2017/5/1916:25 <br>
 *
 * @author 周志辉
 */
public class EncodeTest {

    static Charset[] charsets = new Charset[5];
    static String[] charsetNames = {"UTF-8", "GBK", "ISO-8859-1", "GB2312", "UTF-16"};
    static {
        for (int i = 0; i < charsetNames.length; i++) {
            charsets[i] = Charset.forName(charsetNames[i]);
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str  = "一二三四五六七";
        byte[] bytes = str.getBytes("GBK");
        String temp;
        for (String charsetName : charsetNames) {
            temp = new String(bytes, charsetName);
            for (String name : charsetNames) {
                for (String s : charsetNames) {
                    System.out.print(String.format("%15s", name) + String.format("%15s", s) + "\t\t");
                    System.out.println(new String(temp.getBytes(name), s));
                }
            }
        }



    }
}
