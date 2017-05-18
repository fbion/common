package test;

import test.utils.TestUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.params.HttpClientParams;

/**
 * Created by Administrator on 2016/10/13.
 */
@SuppressWarnings("deprecation")
public class Test24 {
    public static void test1() throws IOException {
        File file = new File("f:\\data.txt");
        FileOutputStream outputStream = new FileOutputStream(file);
        FileChannel channel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        String string = "java nio";
        buffer.put(string.getBytes());
        buffer.flip();     //此处必须要调用buffer的flip方法
        channel.write(buffer);
        channel.close();
        outputStream.close();
        HttpClientParams.isRedirecting(null);
    }

    public static String method1(List<String> list) {
        System.out.println("String");
        return "";
    }

    public static String method(List<Integer> list) {
        System.out.println("Integer");
        return "";
    }

    public static void main(String[] args) throws IOException {
//        SubTest.test();
//        SuperTest.test();
//        System.out.println("123".matches("[1-9]\\d{0,3}"));

//        int i = 1;
//        i = i++ + i++ + ++ i;
//        System.out.println(i);
        String str = "jakldjfl 123123 akldfj ";
        Pattern p = Pattern.compile("(.*?)(\\d+)(.*?)");
        Matcher m = p.matcher(str);
        while(m.find()) {
            System.out.println(m.group(1));
        }
    }
}
class SubTest extends SuperTest{
    public static void test() {
        System.out.println("SubTest");
    }
}

class SuperTest{
    public static void test() {
        System.out.println("SuperTest");
    }
}