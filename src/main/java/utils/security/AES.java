package utils.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;


/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：AES</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/17 15:36<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/17 15:36<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class AES {

    // 加密
    public static String encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    // 加密
    public static String encrypt(byte[] sSrc, byte[] sKey) throws Exception {
        return encrypt(sSrc, sKey, "AES/ECB/PKCS5Padding");
    }

    // 加密
    public static String encrypt(byte[] sSrc, byte[] sKey,String aes) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length!= 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        SecretKeySpec skeySpec = new SecretKeySpec(sKey, "AES");
        Cipher cipher = Cipher.getInstance(aes);//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc);
        System.out.println(Arrays.toString(encrypted));
        System.out.println(encrypted.length);

        byte[] bytes = new byte[32];
        System.arraycopy(encrypted, 0 , bytes, 0, 32);
        return new Base64().encodeToString(bytes);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }


//    /**解密
//     * @param content  待解密内容
//     * @param password 解密密钥
//     * @return
//     */
//    public static byte[] decrypt(byte[] content, String password) {
//        try {
//            KeyGenerator kgen = KeyGenerator.getInstance("AES");
//            kgen.init(128, new SecureRandom(password.getBytes()));
//            SecretKey secretKey = kgen.generateKey();
//            byte[] enCodeFormat = secretKey.getEncoded();
//            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
//            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
//            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
//            byte[] result = cipher.doFinal(content);
//            return result; // 加密
//        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    // 解密
    public static byte[] decrypt(byte[] encrypted, byte[] raw, String aes,String charset) throws Exception {
        try {
            // 判断Key是否为空
            if (raw == null) {
                System.out.print("Key为空null");
                return null;
            }
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
//            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            Cipher cipher = Cipher.getInstance(aes);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            try {
                byte[] original = cipher.doFinal(encrypted);
//                String originalString = new String(original,charset);
                return original;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

    // 解密
    public static byte[] decrypt(String sSrc, byte[] raw, String aes, String charset) throws Exception {
        byte[] encrypted = new Base64().decode(sSrc);//先用base64解密
        return decrypt(encrypted, raw, aes, charset);
    }

    // 解密
    public static byte[] decrypt(byte[] content, byte[] raw, String aes) throws Exception {
        return decrypt(content, raw, aes, "utf-8");
    }

    // 解密
    public static byte[] decrypt(byte[] content, byte[] raw) throws Exception {
        return decrypt(content, raw, "AES/ECB/PKCS5Padding");
    }

//    // 解密
//    public static String decrypt(String sSrc, byte[] raw, String aes) throws Exception {
//        return decrypt(sSrc, raw, aes, "utf-8");
//    }

    // 解密
    public static byte[] decrypt(String sSrc, byte[] raw, String aes) throws Exception {
        return decrypt(sSrc, raw, aes, "utf-8");
    }

    // 解密
    public static byte[] decrypt(String sSrc, byte[] raw) throws Exception {
        return decrypt(sSrc,raw,"AES/ECB/PKCS5Padding");
    }

    // 解密
    public static byte[] decrypt(String sSrc, String sKey, String aes,String charset) throws Exception {
        // 判断Key是否为空
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        byte[] raw = sKey.getBytes(charset);
        return decrypt(sSrc, raw, aes, charset);
    }

    // 解密
    public static byte[] decrypt(String sSrc, String sKey, String aes) throws Exception {
        return decrypt(sSrc, sKey, aes, "utf-8");
    }

    // 解密
    public static byte[] decrypt(String sSrc, String sKey) throws Exception {
        return decrypt(sSrc, sKey, "AES/ECB/PKCS5Padding");
    }
}

