package work.qizhi.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/6/16.
 */
public class Test {
    public static void test1() {
        String str = "美甲 足疗 代驾";
        String[] array = str.split(" ");
        StringBuffer sb = new StringBuffer();
        for (String s : array) {
            sb.append("\"" + s + "\", ");
        }
        System.out.println(sb.toString().replaceAll(", $", ""));
    }

    public static void test2() {
        String str = "美甲 足疗 代驾";
        String[] array = str.split(" ");
        StringBuffer sb = new StringBuffer();
        for (String s : array) {
            sb.append("Array(\"" + s + "\"), ");
        }
        System.out.println(sb.toString().replaceAll(", $", ""));
    }

    public static void main(String[] args) {
//        test2();
        String str = "123";
        String s = str;
        System.out.println(s == str);
        System.out.println(s.equals(str));
    }

    private static Pattern pattern = null;
    public static String StringFilter(String str) {
        // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        if (pattern == null) {
            String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\]./?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\_～《》｛｝×＃￥％＆]";
            pattern = Pattern.compile(regEx);
        }
        Matcher m = pattern.matcher(str);
        return m.replaceAll("").trim();
    }

}
