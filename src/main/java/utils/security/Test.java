package utils.security;

import utils.security.temp.Encrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Test</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/17 9:21<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/17 9:21<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class Test {

    private static byte[] byteReadFile(String path) {
        File file = new File(path);
        if(!file.exists()) {
            return new byte[]{};
        }
        byte[] result = new byte[(int)file.length()];
        try(FileInputStream fis = new FileInputStream(file)) {
            fis.read(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String readFile(String path, String charset) {
        File file = new File(path);
        if(!file.exists()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), charset))){
            String line = null;
            while((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
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

    public static void test() throws Exception {
        String[] src = {"12345678", "123456", "1qaz@WSX"};
        String[] enc = {"LU3k1id4Z27A3bgyV/yvbfvv8VcZuh8oXWxsPf/Dgok=" ,"4b3Ak36ObSkK5OS5HqRRx17pDnGXlOc9JWK0m1rtqfY=", "67Sb4eY68+5aGS5SGJb6Svvv8VcZuh8oXWxsPf/Dgok="};
        String magic = "::::MAGIC::::";

        String hudsonSecretKey = readFile("D:\\py\\hudson.util.Secret", "UTF-8");

        String masterKey = readFile("D:\\py\\master.key", "UTF-8");

        String hashedMasterKey = new Encrypt().SHA256(masterKey);

        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] tempHash = digest.digest(masterKey.getBytes("UTF-8"));
        byte[] hash = new byte[16];
        System.arraycopy(tempHash, 0 , hash, 0, 16);
        byte[] bytes = byteReadFile("D:\\py\\hudson.util.Secret");
        byte[] bytes2 = byteReadFile("D:\\py\\master.key");
        byte[] tempHash2 = AES.decrypt(bytes, hash);
        byte[] middleHash = new byte[16];
//        System.out.println(Arrays.toString(tempHash2));
        System.out.println("解密结果长度过：" + tempHash2.length);
        System.out.println(Arrays.toString(tempHash2));
        System.arraycopy(tempHash2, 0 , middleHash, 0, 16);



        for (int i = 0; i < Math.min(src.length, enc.length); i++) {
            byte[] temp = (src[i] + magic).getBytes("utf-8");
            byte[] bytes1 = new byte[temp.length + (temp.length % 16 == 0 ? 0 : (16- temp.length % 16))];
            System.arraycopy(temp, 0 , bytes1, 0, temp.length);
            for (int j = temp.length; j < bytes.length; j++) {
                bytes[j] = (byte)(bytes.length - temp.length);
            }
//            System.out.println(new String(bytes, "utf-8"));
//            System.out.println(Arrays.toString(bytes));
            System.out.println(enc[i]);
            System.out.println(AES.encrypt(bytes, middleHash));
            System.out.println(enc[i].equals(AES.encrypt(bytes, middleHash)));
        }
    }


    public static void main(String[] args) throws Exception {
//        System.out.println((int)',');
//        System.out.println((int)'&');

//        System.out.println((int)'-');
//        System.out.println((int)'M');
//        System.out.println((int)'(');
//        System.out.println((int)']');
//        System.out.println((int)'l');
//        System.out.println((int)'=');
//        test();


        Pattern p = Pattern.compile("([^-])-([^-]{3})");
        Matcher m = p.matcher("3-001");
        if(m.find()) {
            System.out.println(m.group(1));
            System.out.println(m.group(2));
        }
    }
}
