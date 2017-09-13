package advance.JVM;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：jdk1.6的javac可编译过，其他版本的编译器可能会报错 <br>
 * 创建时间: 2017/9/1311:29 <br>
 *
 * @author 周志辉
 */
public class GenericTypes {

//    public static String method(List<String> list) {
//        System.out.println("invoke method(List<String> list");
//        return "123";
//    }

    public static int method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list");
        return 0;
    }


    public static void main(String[] args) {
//        method(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }
}
