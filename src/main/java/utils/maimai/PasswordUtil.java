package utils.maimai;

import java.util.Random;

/**
 * Created by zzh on 2016/11/4.
 */
public class PasswordUtil {

    private static final char[] CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private static final int LENGTH = 6;

    private static final Random rand = new Random(System.currentTimeMillis());

    /**
     * 产生LENGTH长度的随机密码
     * @return
     */
    public static String createInitPassword() {
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < LENGTH; i++) {
            sb.append(CHARS[rand.nextInt(CHARS.length)]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(createInitPassword());
    }
}
