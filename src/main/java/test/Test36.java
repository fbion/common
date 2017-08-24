package test;

import com.beust.jcommander.internal.Lists;

import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1811:57 <br>
 *
 * @author 周志辉
 */
public class Test36 {


    /**
     * 字符串里出现三遍及以上的字符去掉
     */
    public static void test() {
        String str = "11adfaasfwqpkwefapadgjqw....$$$^^^[[[";
        Pattern p = Pattern.compile("(.*)(.)(.*)\\2(.*)\\2(.*)");
        String temp = str;
        Matcher m = p.matcher(temp);
        while (m.find()) {
            System.out.print(m.group(2) + "\t");
            temp = temp.replace(m.group(2), "");
            m = p.matcher(temp);
        }
        ;
        System.out.println(temp);

        //定义一个字符串
        String string = "11adfaasfwqpkwefapadgjqw....$$$^^^[[[";
        //遍历字符串的所有字符
        for (int i = 0; i < string.length(); i++) {
            //取得第i个字符，也可以用Character.toString(string.charAt(i));
            String s = string.substring(i, i + 1);
            //如果通过s在这个字符串中出现了至少3次，输出该字符
            if (Pattern.matches(".*" + Pattern.quote(s) + ".*" + Pattern.quote(s) + ".*" + Pattern.quote(s) + ".*", string)) {
                System.out.print(s + "\t");
            }
        }
    }


    static String str = "11adfaasfwqpkwefapadgjqw....$$$^^^[[[";

    public static void main(String[] args) {
        String test = "11adfaasfwqpkwefapadgjqw....$$$^^^[[[";
//        List<List<String>> lists =
//                Arrays.asList(str.split("")).stream().collect(Collectors.groupingBy(n -> n))
//                        .values().stream()
//                        .filter(n -> n
//                                .size() >= 3).collect(Collectors.toList());
//        System.out.println(lists);

        Arrays.asList(str.split("")).stream().collect(Collectors.groupingBy(
                Function.identity(), Collectors.counting()
        )).entrySet().stream().forEach(n -> {
            if (n.getValue() >= 3) {
                str = str.replace(n.getKey(), "");
            }
        });
        System.out.println(str);

        Arrays.asList(test.split(""))
                .stream()
                .collect(Collectors.groupingBy(n -> n))
                .values()
                .stream()
                .filter(n -> n.size() > 3)
                .reduce(Lists.newArrayList(), (prev, next) -> {
                    prev.addAll(next);
                    return prev;
                })
                .stream()
                .distinct()
                .forEach(System.out::print);
    }
}
