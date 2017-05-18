package Algorithms.recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/5.
 */
public class MapCombination {

    public static void test(Map<String, String> map)
    {
        ArrayList<Map.Entry> result = new ArrayList<Map.Entry>();
        List<Map.Entry> list = new ArrayList<>(map.entrySet());
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ":");
            iteratorAllSubMap(list, i + 1, result);
        }
    }

    public static void iteratorAllSubMap(List<Map.Entry> list, int n, ArrayList<Map.Entry> result)
    {
        ArrayList<Map.Entry> newList = (ArrayList<Map.Entry>)result.clone();
        if(list.size() == n)
        {
            newList.addAll(list);
            verify(newList);
        }
        else if (n == 0 || list.size() < n)
        {
            return;
        }
        else if(n == 1)
        {
            iteratorAllSubMap(list.subList(1, list.size()), n, newList);
            newList = (ArrayList<Map.Entry>)result.clone();
            newList.add(list.get(0));
            verify(newList);
        }
        else
        {
            iteratorAllSubMap(list.subList(1, list.size()), n, newList);
            newList = (ArrayList<Map.Entry>)result.clone();
            newList.add(list.get(0));
            iteratorAllSubMap(list.subList(1, list.size()), n - 1, newList);
        }
    }

    public static void verify(ArrayList<Map.Entry> list)
    {
        Map map = new HashMap<>();
        for (Map.Entry entry : list) {
            map.put(entry.getKey(), entry.getValue());
        }
        System.out.println(map);
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(i + "", i + "");
        }
        System.out.println("待遍历的Map: " + map);
        test(map);
    }
}
