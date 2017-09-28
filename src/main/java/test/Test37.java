package test;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * 描述： <br>
 * 创建时间: 2017/9/159:51 <br>
 *
 * @author 周志辉
 */
public class Test37 {

    public static String test() throws Exception {
        try(BufferedReader br = new BufferedReader(new FileReader("d:\\1.txt"))) {
            return br.readLine();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(test());
    }
}
