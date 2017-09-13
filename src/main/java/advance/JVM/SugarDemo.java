package advance.JVM;

import java.util.Arrays;
import java.util.List;

/**
 * 描述： Java语法糖演示，泛型，自动装箱、拆箱，遍历循环<br>
 * 备注：编译后，反编译字节码，可以看到语法糖去除后的语法
 * 创建时间: 2017/9/1311:55 <br>
 *
 * @author 周志辉
 */
public class SugarDemo {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }
        System.out.println(sum);
    }
}
/*

  */