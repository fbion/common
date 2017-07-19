package utils.security;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 描述：密码加密 <br>
 * 创建时间: 2017年6月15日 上午11:01:07 <br>
 *
 * @author 汤京
 */

public final class Encoder {
    /**
     * 利用MD5进行加密
     * @param password  待加密的字符串
     * @return  加密后的字符串
     * @throws NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws UnsupportedEncodingException  
     */
    public static String EncoderByMd5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newPassword = base64en.encode(md5.digest(password.getBytes("utf-8")));
        return newPassword;
    }


    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(EncoderByMd5("12345"));
    }
}
