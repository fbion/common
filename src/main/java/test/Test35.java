package test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1512:42 <br>
 *
 * @author 周志辉
 */
public class Test35 {

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("123", "123");
        map.put("357", "357");
        map.put("234", "234");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}