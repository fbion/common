package work.shyk;


import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

/**
 * DESede对称加密算法工具类
 *
 * @author longjiazuo
 * @Description
 * @created 2015年8月1日 下午7:23:34
 * @history
 * @see
 */
public class DESedeUtils {

    public static final int ENCRYPT_MODE = Cipher.ENCRYPT_MODE;

    public static final int DECRYPT_MODE = Cipher.DECRYPT_MODE;

    private static final String ALGORITHM = "DESede";

    private static final Charset UTF8 = Charset.forName("UTF-8");

    private Cipher cipher = null;

    private int opmode = 0;


    /**
     * 加密或者解密的初始化入口
     *
     * @param mode
     * @param key
     * @return
     * @Description
     * @author longjiazuo
     * @created 2015年11月20日 下午4:56:25
     */
    public synchronized boolean init(int mode, String key) {
        if (opmode != 0) {
            return true;
        }

        if (mode != ENCRYPT_MODE && mode != DECRYPT_MODE) {
            return false;
        }

        if (key == null || key.isEmpty()) {
            return false;
        }

        try {
            cipher = Cipher.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } finally {
            if (cipher == null) {
                return false;
            }
        }
        Key secKey = getSecKey(key);
        if (secKey == null) {
            return false;
        }
        try {
            cipher.init(mode, secKey, new SecureRandom());
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return false;
        }
        opmode = mode;
        return true;
    }


    private static Key getSecKey(String key) {
        SecretKey securekey = null;
        try {
            byte[] material = Arrays.copyOf(
                    Base64.decodeBase64(key.getBytes(UTF8)), 24);
            DESedeKeySpec keySpec = new DESedeKeySpec(material);
            SecretKeyFactory keyFactory = SecretKeyFactory
                    .getInstance(ALGORITHM);
            securekey = keyFactory.generateSecret(keySpec);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return securekey;
    }


    // 加密算法,用法固定
    public synchronized String encrypt(String data) {
        if (opmode != ENCRYPT_MODE) {
            return null;
        }
        if (data == null) {
            return null;
        }
        byte[] encData = null;
        try {
            encData = cipher.doFinal(data.getBytes(UTF8));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        if (encData == null) {
            return null;
        }
        return new String(Base64.encodeBase64(encData), UTF8);
    }


    // 解密算法,用法固定
    public synchronized String decrypt(String data) {
        if (opmode != DECRYPT_MODE) {
            return null;
        }
        if (data == null) {
            return null;
        }
        byte[] decData = null;
        try {
            decData = cipher.doFinal(Base64.decodeBase64(data.getBytes(UTF8)));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        if (decData == null) {
            return null;
        }
        return new String(decData, UTF8);
    }

    /**
     *
     * 生成密钥，java6只支持56位密钥，bouncycastle支持64位密钥
     * @return byte[] 二进制密钥
     * */
    public static byte[] initkey() throws Exception{

        //实例化密钥生成器
        KeyGenerator kg= KeyGenerator.getInstance("DESEDE");
        //初始化密钥生成器
        kg.init(168);
        //生成密钥
        SecretKey secretKey=kg.generateKey();
        //获取二进制密钥编码形式
        return secretKey.getEncoded();
    }


    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 100000; i++) {
//            System.out.print(i + "\t");
            String secretKey = "v8uYGQsNs7V5MQQ33wJ1+BykhUm/6mL4";
            SSOToken token = new SSOToken();
            // 设置token的内容和时间戳
            token.setUserName(i + "");
            token.setDisplayName(i + "");
            token.setTimestamp(System.currentTimeMillis());
            // 解析token
            String tokenStr = JSON.toJSONString(token);
            DESedeUtils encoder = new DESedeUtils();
            // 利用secretKey初始化DESedeUtils加密
            encoder.init(DESedeUtils.ENCRYPT_MODE, secretKey);
            // 加密token
            String encToken = encoder.encrypt(tokenStr);

            DESedeUtils decoder = new DESedeUtils();
            // 利用secretKey初始化DESedeUtils加密
            decoder.init(DESedeUtils.DECRYPT_MODE, secretKey);
            String decToken = decoder.decrypt(encToken);
            SSOToken decT = JSON.parseObject(decToken, SSOToken.class);

            if(!(i + "").equals(decT.getUserName())) {
                System.out.println(decT.getUserName());
            }
        }
        System.out.println("over");

        String secret = com.sun.org.apache.xml.internal.security.utils.Base64.encode(initkey());
        String secret2 = Base64.encodeBase64String(initkey());
        System.out.println("密钥：" + secret + "\t位数 : " + secret.length());
        System.out.println("密钥：" + secret2 + "\t位数 : " + secret2.length());
//        for (Object obj : java.security.Security.getAlgorithms("Cipher")) {
//            System.out.println(obj);
//        }
    }
}
