package test.base;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2016/6/6.
 */
public class TestCode {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "f2c63c5a";
        Long in = Long.valueOf(str,16);
        System.out.println(in);
        System.out.println(System.currentTimeMillis());
    }
}
