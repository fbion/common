package test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/8/12.
 */
public class Test13 {
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);
        return (n < 0) ? 1 : (n >= (1 << 30)) ? (1 << 30) : n + 1;
    }

    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(2, 2);
        map.put(5, 5);
        map.put(8, 8);
        map.put(3, 3);
        map.put(1, 1);
//        System.out.println(map);
//        for (Map.Entry<Integer, Integer>  entry: map.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
//
//        System.out.println(tableSizeFor(10000000));

        String str =" val startDayStr = if(from.trim == \"\"){ todayStr } else { from.trim }";
        Pattern p1 = Pattern.compile("s?\"([^\"]*[\u4e00-\u9fa5]+[^\"]*)\"");
        Matcher m1 = p1.matcher(str);
        if(m1.find()) {
            System.out.println(m1.group());
            System.out.println(m1.group(1));
        }

        Pattern p2 = Pattern.compile("[\u4e00-\u9fa5]+");
        Matcher m2 = p2.matcher(str);
        if(m2.find()) {
            System.out.println(m2.group());
        }
    }
}
