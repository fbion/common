package test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 描述： <br>
 * 创建时间: 2017/8/2811:47 <br>
 *
 * @author 周志辉
 */
public class Test37 {

    public static LinkedList<String> fetch(LinkedList<String> list, int n) {
        if(n > list.size()) {
            System.out.println("取的数据量超过list总数据量");
            System.exit(1);
        }
        LinkedList<Integer> temp = new LinkedList<>();
        Set<String> set = new LinkedHashSet<>();
        for (int i = 0; i < list.size(); i++) {
            if(!set.add(list.get(i))) {
                temp.add(i);
            }
        }
        LinkedList<String> result;
        int count = 0;
        if(n <= set.size()) {                       //所取元素数量不大于不重复元素数量，直接从set里按顺序取
            result = new LinkedList<>();
            for (String s : set) {
                result.add(s);
                if(++count == n) {
                    break;
                }
            }
        } else {
            //超出set元素数，则算出一共比原list少多少个，直接克隆一个list，
            // 然后从原list按temp里保存的元素序号，从后面减去多余的
            result = (LinkedList<String>)list.clone();
            count = list.size() - n;
            int size = temp.size();
            for (int i = 0; i < count; i++) {
                result.remove(temp.get(size - i- 1).intValue());
            }
        }
        return result;
    }
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.addAll(Arrays.asList("abbcccdde".split("")));
        System.out.println(fetch(list, 2));
        System.out.println(fetch(list, 5));
        System.out.println(fetch(list, 6));
        System.out.println(fetch(list, 7));
        System.out.println(fetch(list, 8));
        System.out.println(fetch(list, 9));
    }
}
