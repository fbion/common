package ebook.thinking.chapter17;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2016/3/21.
 */
public class MapPerformance {
    static List<Test<Map<Integer, Integer>>> tests = new ArrayList<>();
    static {
        tests.add(new Test<Map<Integer, Integer>>("put"){
            @Override
            int test(Map<Integer, Integer> mp, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    mp.clear();
                    for (int j = 0; j < size; j++) {
                        mp.put(j, j);
                    }
                }
                return loops * size;
            }
        });
        tests.add(new Test<Map<Integer, Integer>>("get"){
            @Override
            int test(Map<Integer, Integer> mp, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++) {
                    for (int j = 0; j < span; j++) {
                        mp.get(j);
                    }
                }
                return loops * span;
            }
        });
        tests.add(new Test<Map<Integer, Integer>>("iterate"){
            @Override
            int test(Map<Integer, Integer> mp, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator iterator = mp.entrySet().iterator();
                    while(iterator.hasNext()){
                        iterator.next();
                    }
                }
                return loops * mp.size();
            }
        });
    }

    public static void main(String[] args) {
        if(args.length > 0) {
            Tester.defaultParams = TestParam.array(args);
        }
        Tester.fieldWidth = 10;
        Tester.run(new TreeMap<Integer, Integer>(), tests);
        Tester.run(new HashMap<Integer, Integer>(), tests);
        Tester.run(new LinkedHashMap<Integer, Integer>(), tests);
        Tester.run(new IdentityHashMap<Integer, Integer>(), tests);
        Tester.run(new WeakHashMap<Integer, Integer>(), tests);
        Tester.run(new Hashtable<Integer, Integer>(), tests);

    }
}
