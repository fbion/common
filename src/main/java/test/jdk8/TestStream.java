package test.jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Administrator on 2016/5/12.
 */
public class TestStream {

    public static void test() {
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList("a, b, c, d, e, h, i, j, k".split(",")));
//        list[0];
//        System.out.println(list.stream().reduce((result, element) -> {
//            if (element > 2) {
//                result = element;
//            }
//            return result;
//        }).get());
//
//
//        System.out.println(list.stream().reduce((result, element) -> {
//            return result + element;
//        }).get());
//        System.out.println(list.stream().mapToInt((i) -> i).sum());


        System.out.println(list.stream().skip(3).collect(Collectors.toList()));

        System.out.println(IntStream.range(5, 6).mapToObj(i -> list.get(i)).collect(Collectors.toList()));
        System.out.println(IntStream.range(0, list.size()).filter(i -> i % 3 == 0).mapToObj(i -> list.get(i)).collect(Collectors.toList()));

        //        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            list.add(i);
//        }
//        long count = list.stream().filter((x) -> x % 3 == 1).count();
//        System.out.println(count);
//        List<String> strList = new ArrayList<>();
//        strList.add("zzz1");
//        strList.add("aaa2");
//        strList.add("bbb2");
//        strList.add("fff1");
//        strList.add("fff2");
//        strList.add("aaa1");
//        strList.add("bbb1");
//        strList.add("zzz2");
//        strList.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);

//        String[] a1 = new String[]{"1", "2"};
//        String[] a2 = new String[]{"3", "4"};
//        List<String> l = new ArrayList<>();
//        l.addAll(Arrays.asList(a1));
//        l.addAll(Arrays.asList(a2));
//        String[] r = new String[a1.length + a2.length];
//        l.toArray(r);
//        System.out.println(Arrays.deepToString(r));
//
//
//        List<Integer> list2 = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            list.add(i);
//        }
//        System.out.println(list);
//        List<Integer> list3 = list.stream().filter((x) -> x % 3 == 0).collect(Collectors.toList());
//        System.out.println(list3);

        List<String> list1 = new ArrayList<>();
        list1.addAll(Arrays.asList("1234567890".split("")));
        System.out.println(list1.stream().filter(t -> Integer.parseInt(t) < 5).collect(Collectors.toList()));
    }

    public static List<Integer> createList(int size) {
        List<Integer> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            result.add(i);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list;
        List<Integer> result;
        long start;
        int[] sizes = {10_000_000, 100_000_000};
        int[] intervals = {100, 1000, 10_000};

        for (int size : sizes) {
            System.out.println("size of list : " + size);
            list = createList(size);
            for (int interval : intervals) {
                //每隔interval个取一个元素组成新的list
                List<Integer> tempList = list;
                System.out.println("\t\tinterval : " + interval);
                start = System.nanoTime();
                result = IntStream.range(0, list.size()).filter(j -> j % interval == 0).mapToObj(j -> tempList.get(j)).collect(Collectors.toList());
                System.out.println("\t\t\t\tsize : "  + result.size());
                System.out.println("\t\t\t\tduration1(ns) : " + (System.nanoTime() - start));

                start = System.nanoTime();
                int temp = list.size() / interval;
                result = new ArrayList<>(temp);
                for (int i = 0; i < temp; i++) {
                    result.add(list.get(i * interval));
                }
                System.out.println("\t\t\t\tsize : "  + result.size());
                System.out.println("\t\t\t\tduration2(ns) : " + (System.nanoTime() - start));
            }
        }
    }
}
