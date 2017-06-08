package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述： <br>
 * 创建时间: 2017/6/19:52 <br>
 *
 * @author 周志辉
 */
public class Test34 {
    static int i = 0;
    public static int add(int num) {
        return num++;
    }
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("12345".split("")));
        for (int j = 0; j < 10; j++) {
            int m = add(Integer.parseInt(list.get(j % 5)));
            System.out.print(m);
        }
    }
}
