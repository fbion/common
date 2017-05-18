package utils.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by joymufeng on 2015/7/2.
 */
public class Des {
    private static byte[] keyData = new byte[]{112, 63, 118, 111, 78, 98, 101, 117};
    public static byte[] encrypt(String text) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(keyData, "Des");
        Cipher cipher = Cipher.getInstance("Des");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(text.getBytes("UTF-8"));
    }

    public static byte[] decrypt(byte[] data) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(keyData, "Des");
        Cipher cipher = Cipher.getInstance("Des");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }
}
