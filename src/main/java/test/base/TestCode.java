package test.base;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/6.
 */
public class TestCode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = new Date().toString() + "焦页";
//        str = "百度一下，你就知道";
        String[] charsets = {"UTF-8", "UTF-16", "GBK", "GB2312", "ISO-8859-1"};
        for (int i = 0; i < charsets.length; i++) {
            for (int j = 0; j < charsets.length; j++) {
                System.out.println("charset1 = " + charsets[i] + "\tcharset2 = " + charsets[j] + "\tresult = " + new String(str.getBytes(charsets[i]), charsets[j]));
            }
        }
    }
}
