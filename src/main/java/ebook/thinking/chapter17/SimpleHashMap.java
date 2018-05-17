package ebook.thinking.chapter17;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/3/19.
 */
public class SimpleHashMap<K, V> extends AbstractMap<K, V>{

    //数组容量取质数，可以使Key值的HashMap分布在数组里更均匀
    static final int SIZE = 997;

    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    public V put(K key, V value){
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] == null){
            buckets[index] = new LinkedList<MapEntry<K, V>>();
        }
        boolean found = false;
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        for (MapEntry<K, V> mapEntry : bucket) {
            if(mapEntry.getKey().equals(key)){
                oldValue = mapEntry.getValue();
                mapEntry.setValue(value);
                found = true;
            }
        }
        if(!found){
            bucket.add(new MapEntry<K, V>(key, value));
        }
        return oldValue;
    }

    public V get(Object key){
        int index = Math.abs(key.hashCode()) % SIZE;
        if(buckets[index] != null){
            for (MapEntry<K, V> mapEntry : buckets[index]) {
                if(mapEntry.getKey().equals(key)){
                    return mapEntry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if(bucket == null){
                continue;
            }
//            for (MapEntry<K, V> mapEntry : bucket) {
//                set.add(mapEntry);
//            }
            bucket.forEach((mapEntry)->set.add(mapEntry));
        }
        return set;
    }

    public static void main(String[] args) {
        SimpleHashMap<String, String> simpleHashMap = new SimpleHashMap<>();
        simpleHashMap.put("1", "1");
        simpleHashMap.put("2", "2");
        simpleHashMap.put("3", "3");
        System.out.println(simpleHashMap.get("1"));
        System.out.println(simpleHashMap.entrySet());
    }
}

class MapEntry<K, V> implements Map.Entry<K, V>{
    K key;
    V value;

    public MapEntry(K key, V value){
        this.key = key;
        this.value = value;
    }
    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public V setValue(V value) {
        this.value = value;
        return value;
    }
}
