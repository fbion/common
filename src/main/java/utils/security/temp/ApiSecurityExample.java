package utils.security.temp;

import org.apache.commons.codec.binary.Base64;
import utils.security.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：ApiSecurityExample</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/17 14:19<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/17 14:19<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class ApiSecurityExample {
    public static void main(String[] args) {
        try {
            String secret = "secret";
            String message = "Message";

            String masterKey = Test.readFile("D:\\py\\master.key", "UTF-8");

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(masterKey.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

            System.out.println(Arrays.toString(sha256_HMAC.doFinal(message.getBytes())));
            String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
            System.out.println(hash);
        }
        catch (Exception e){
            System.out.println("Error");
        }
    }
}
