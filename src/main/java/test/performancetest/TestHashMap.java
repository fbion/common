package test.performancetest;


import java.util.*;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/3/23.
 */
public class TestHashMap
{
    static Map<String, Integer> table = new Hashtable<>();
    static Map<String, Integer> map = new HashMap<>();
    static Map<String, Integer> concurrentMap = new ConcurrentHashMap<String, Integer>();

    static void init(){
        table.put("test", 0);
        map.put("test", 0);
        concurrentMap.put("test", 0);
    }

    static class TestHashTableTask implements Runnable
    {
        @Override
        public void run() {
            synchronized (table)
            {
                table.put("test", new Integer(table.get("test")) + 1);
            }
        }
    }

    static class TestHashMapTask implements Runnable
    {
        @Override
        public void run() {
            synchronized (map)
            {
                map.put("test", new Integer(map.get("test")) + 1);
            }
        }
    }

    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            init();
            System.out.print("\t\t\tHashtable:" + table);
            System.out.print("\tHashMap:" + map);
            System.out.println("\tConcurrentMap:" + concurrentMap);
            System.out.print("第" + (i + 1) + "次 : ");
            for (int j = 0; j < 50; j++) {
                cachedThreadPool.submit(() -> table.put("test", new Integer(table.get("test")) + 1));//concurrentMap
                cachedThreadPool.submit(() -> map.put("test", new Integer(map.get("test")) + 1));
                cachedThreadPool.submit(() -> concurrentMap.put("test", new Integer(concurrentMap.get("test")) + 1));
            }
            Thread.currentThread().yield();
            TimeUnit.SECONDS.sleep(10);
            System.out.print("\tHashtable:" + table);
            System.out.print("\tHashMap:" + map);
            System.out.println("\tConcurrentMap:" + concurrentMap);
            System.out.println();

        }
    }
}
