package utils.security.temp;

import utils.security.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：StringEncrypt</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/17 14:15<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/17 14:15<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class StringEncrypt {
    /**
     * 对字符串加密,加密算法使用MD5,SHA-1,SHA-256,默认使用SHA-256
     *
     * @param strSrc
     *            要加密的字符串
     * @param encName
     *            加密类型
     * @return
     */
    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;

        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    public static void main(String args[]){
        String s= StringEncrypt.Encrypt(Test.readFile("D:\\py\\master.key", "UTF-8"), "");
        System.out.println(s);
    }
}
