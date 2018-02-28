/**
 * description： <br>
 * createTime: 2017/12/717:49 <br>
 *
 * @author zzh
 */
public class Test {

    public static void main(String[] args) {
        String str = "/cts-web/cts-master/getMenus";
        String temp = str.replaceFirst("/", "");
        temp = temp.substring(temp.indexOf("/") + 1);
        System.out.println(temp);
        System.out.println(temp.substring(0, temp.indexOf("/")));
    }
}
