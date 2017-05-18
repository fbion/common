package test.recent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2015/12/26.
 */
public class TestSecurity {
    public static void main(String[] args) throws FileNotFoundException {
        System.setSecurityManager(new SecurityManager());
        System.out.println(System.getSecurityManager());
        FileInputStream fis = new FileInputStream("f:\\1.txt");
        System.out.println(System.getProperty("1234.txt"));
    }
}
