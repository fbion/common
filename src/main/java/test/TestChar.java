package test;

/**
 * 描述： <br>
 * 创建时间: 2017/7/288:51 <br>
 *
 * @author 周志辉
 */
public class TestChar {

    public static void main(String[] args) {
        char c1,c2;
        for (c1 = 0; c1 < Character.MAX_VALUE; c1++) {
            for (c2 = 0; c2 < Character.MAX_VALUE; c2++) {
                if(c1 != c2) {
                    if((Character.toLowerCase(c1) == Character.toLowerCase(c2)) ^
                            (Character.toUpperCase(c1) == Character.toUpperCase(c2))) {
                        System.out.println("大写和小写中一个相等一个不等的字符： " + c1 + "," + c2);
                    }
                }
            }
        }
    }
}
