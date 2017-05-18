package test.base;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Administrator on 2016/4/23.
 */
public class TestCopyOnWriteArrayList {
    public static void main(String[] args) throws NoSuchMethodException {
        List<String> list1 = new CopyOnWriteArrayList<>();
        System.out.println("HashCode of list1 : ");
        System.out.println("\tBefore add 1 :" + list1.hashCode());
        list1.add("1");
        System.out.println("\tAfter add 1 :" + list1.hashCode());
        list1.add("2");
        System.out.println("\tAfter add 2 :" + list1.hashCode());
        list1.remove("2");
        System.out.println("\tAfter delete 2 :" + list1.hashCode());

        Map<List, Integer> map = new HashMap<>();
        map.put(list1, 1);
        System.out.println(map.get(list1));
        list1.add("3");
        System.out.println(map.get(list1));
    }
}
