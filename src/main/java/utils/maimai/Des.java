package utils.maimai;

import org.apache.commons.codec.binary.Hex;

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

    public static void main(String[] args) throws Exception {
        System.out.println(Hex.encodeHexString(encrypt("zzh" + System.currentTimeMillis() + System.currentTimeMillis())).length());
        System.out.println(new String(Des.decrypt(Hex.decodeHex("b4764aa613cbb08e0eaf05950dcb538c0a8e698981cdb0dd".toCharArray())), "utf-8"));
        System.out.println((System.currentTimeMillis() + "").length());
    }
}
