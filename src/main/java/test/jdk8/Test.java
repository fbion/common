package test.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * description： <br>
 * createTime: 2017/10/1716:22 <br>
 *
 * @author zzh
 */
public class Test {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1_1", "1_3", "2_12", "3_13", "3_2", "3_3", "2_7", "2_9", "1_11");
        Map<Integer, List<Integer>> map = new LinkedHashMap<>();
        //TODO
//        System.out.println(list.stream().collect(groupingBy((s) -> s.split("_")[0])));
        list.forEach(s -> {
            String[] temp = s.split("_");
            System.out.println(Arrays.deepToString(temp));
            Integer key = new Integer(temp[0]);
            List<Integer> l = map.get(key);
            if (l == null) {
                l = new ArrayList<>();
                map.put(key, l);
            }
            l.add(new Integer(temp[1]));
        });
        System.out.println(map);
    }
}
