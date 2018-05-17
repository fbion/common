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
//        test2();
        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println(i1 == i2);
    }
    public static void test1() throws UnsupportedEncodingException {
        String str  = "一二三四五六七";
        byte[] bytes = str.getBytes("GBK");
        String temp;
        for (String charsetName : charsetNames) {
            for (String name : charsetNames) {
                System.out.print(String.format("%15s", charsetName) + String.format("%15s", name) + "\t\t");
                System.out.println(new String(str.getBytes(charsetName), name));
            }
        }

    }

    public static void test2() throws UnsupportedEncodingException {
        //遍历看哪些编码间乱码可以还原
        String str  = "一二三四五六七";
        for (String charsetName1 : charsetNames) {
            for (String charsetName2 : charsetNames) {
                //用charsetName1把String转byte[],再用charsetName2用byte[]构造String，再用charsetName2把String转成byte[]，
                // 这里用charsetName2构造了String再转回byte[]，感觉像是还原成原来的byte[]，但某些编码之间通过这样的步骤是还原不了的
                //最后再把byte[]用charsetName1转成String
                if(!charsetName1.equals(charsetName2)) {
                    String temp = new String(str.getBytes(charsetName1), charsetName2);
                    String back = new String(temp.getBytes(charsetName2), charsetName1);
                    if(str.equals(back)) {
                        System.out.print(String.format("%15s", charsetName1) + String.format("%15s", charsetName2) + "\t\t");
                        System.out.print(temp + "\t\t\t");
                        System.out.println(back);
                    }
                }
            }
        }
    }
}
