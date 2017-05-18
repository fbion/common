package test.base;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Set;

/**
 * Created by Administrator on 2016/6/6.
 */
public class TestCode {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = new Date().toString() + ": 您好，在使用奇智对话机器人之前，请花三分钟时间阅读本文档，以便您能快速了解如何使用它，并将其完美融合到您的桌面网站，移动网站，微信之中。";
//        str = "百度一下，你就知道";
        String[] charsets = {"UTF-8", "UTF-16", "GBK", "GB2312", "ISO-8859-1"};
        for (int i = 0; i < charsets.length; i++) {
            for (int j = 0; j < charsets.length; j++) {
                System.out.println("charset1 = " + charsets[i] + "\tcharset2 = " + charsets[j] + "\tresult = " + new String(str.getBytes(charsets[i]), charsets[j]));
            }
        }
        System.out.println("123\"".replaceAll("\"", ""));
    }
}
