package utils.security.temp;

import org.apache.commons.codec.binary.Hex;
import utils.security.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：TestSHA</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/17 14:49<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/17 14:49<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class TestSHA {
    public static void main(String args[]){
        String text = "123456123456";
        String masterKey = Test.readFile("D:\\py\\master.key", "UTF-8");
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(masterKey.getBytes("UTF-8"));
            String output = Hex.encodeHexString(hash);
            System.out.println(output);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
