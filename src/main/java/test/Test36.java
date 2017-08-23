package test;

import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1811:57 <br>
 *
 * @author 周志辉
 */
public class Test36 {

    static{
        if(true) {
            System.out.println("123");
            while (true) {
            }
        }
    }

    public static void main(String[] args) {
        ArrayList list1 = new ArrayList();
        list1.addAll(Arrays.asList("123456".split("")));

        ArrayList list2 = new ArrayList();
        list2.addAll(Arrays.asList("789".split("")));

        JSONArray jsonArray1 = JSONArray.fromObject(list1);
        JSONArray jsonArray2 = JSONArray.fromObject(list2);
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(jsonArray1);
        jsonArray.addAll(jsonArray2);
        System.out.println(jsonArray1);
        System.out.println(jsonArray2);
        System.out.println(jsonArray);
    }
}
