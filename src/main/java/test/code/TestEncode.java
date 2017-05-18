package test.code;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/5/17.
 */
public class TestEncode {
    public static void main(String[] args) {
        String s = "%3Cscript%3Ealert(123);%3C/script%3E";
        System.out.println(URLDecoder.decode(s));
        System.out.printf(URLEncoder.encode("<script>alert(123);</script>"));
    }
}
