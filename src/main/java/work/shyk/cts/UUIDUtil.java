package work.shyk.cts;


import java.util.UUID;


/**
 * 项目名称：cts<br>
 * *********************************<br>
 * <P>类名称：UUIDUtil</P>
 * *********************************<br>
 * <P>类描述：获取32位随机字符串工具类</P>
 * 创建人：王东辉<br>
 * 创建时间：2017-1-13 上午10:47:56<br>
 * 修改人：username<br>
 * 修改时间：2017-1-13 上午10:47:56<br>
 * 修改备注：<br>
 *
 * @version 1.0<br>
 */
public class UUIDUtil {

    public UUIDUtil() {
    }


    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号 
        return s.replaceAll("-", "");
    }


    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }


    public static void main(String[] args) {
        String uuid;
        for (int i = 0; i < 10; i++) {
            uuid = getUUID();
            System.out.print(uuid);
            System.out.println( "\t\t" + uuid.length());
        }
    }
}
