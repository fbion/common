package test.code;

import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/6/17.
 */
public class TestLength {
    public static void main(String[] args) {
        String str = "你我他们国家大小颜色";
        String[] charsets = {"UTF-8", "UTF-16", "GBK", "GB2312", "ISO-8859-1"};
        for (String s : str.split("")) {
            System.out.print(s);
            for (String charset : charsets) {
                System.out.print( "\t" + charset + " length : " + s.getBytes(Charset.forName(charset)).length);
            }
            System.out.println();
        }

    }
}
