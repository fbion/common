package test.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Administrator on 2016/4/27.
 */
public class TestProperties {
    public static void main(String[] args) throws IOException {
        Properties p = new Properties();
        p.put("name", "zhangsan");
        p.put("password", "123456");
        p.storeToXML(System.out, "person");
    }
}
