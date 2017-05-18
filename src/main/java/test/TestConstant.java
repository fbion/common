package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Administrator on 2016/8/5.
 */
public class TestConstant {
    public static void main(String[] args) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("f:\\Test.java")))) {
            bw.write("public class Test{\r\n");
            for (int i = 0; i < 70000; i++) {
                bw.write("static final String str" + i + " = \"" + i + "\";\r\n");
            }
            bw.write("}");
            bw.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Over");
    }
}
