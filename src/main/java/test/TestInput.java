package test;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/7/28.
 */
public class TestInput {
    public static void main(String[] args) throws IOException {
//        InputStream ins = System.in;
//        ins.read();
        String str = "你好";
        char[] chars = str.toCharArray();
        System.out.println(chars.length);
    }
}
