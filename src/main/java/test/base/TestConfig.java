package test.base;

import test.utils.TestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2016/7/27.
 */
public class TestConfig {
    public static void main(String[] args) throws IOException {
        InputStream ins = TestUtils.class.getResourceAsStream("/myconfig.properties");
        Properties p = new Properties();
        p.load(ins);
        String s = p.getProperty("name");
        System.out.println(s);
    }
}
