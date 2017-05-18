package work.qizhi.test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/3/30.
 */
public class Test {
//    public String Encrypt(String strSrc) {
//        MessageDigest md=null;
//        String strDes=null;
//
//        byte[] bt=strSrc.getBytes();
//        try {
//
//            md=MessageDigest.getInstance("SHA-1");
//            md.update(bt);
//            strDes=bytes2Hex(md.digest());  //to HexString
//        }catch (NoSuchAlgorithmException e) {
//            System.out.println("Invalid algorithm.");
//            return null;
//        }
//        return strDes;
//    }
//
//    public String bytes2Hex(byte[]bts) {
//        String des="";
//        String tmp=null;
//        for (int i=0;i<bts.length;i++) {
//            tmp=(Integer.toHexString(bts[i] & 0xFF));
//            if (tmp.length()==1) {
//                des+="0";
//            }
//            des+=tmp;
//        }
//        return des;
//    }
//
//    public static void main(String[] args) {
//        BidCreate te=new BidCreate();
//        String strSrc="可以加密汉字.Oh,and english";
//        System.out.println("Source String:"+strSrc);
//        System.out.println("Encrypted String:");
//        System.out.println("Use SHA:"+te.Encrypt(strSrc).toUpperCase());
//    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String str = "jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&noncestr=Wm3WZYTPz0wzccnW&timestamp=1414587457&url=http://mp.weixin.qq.com?params=value";
        String str1 = "0f9de62fce790f9a083d5c99e95740ceb90c27ed";

        System.out.println(str);
        System.out.println("jsapi_ticket=sM4AOVdWfPE4DxkXGEs8VMCPGGVi4C3VM0P37wVUCFvkVAy_90u5h9nbSlYy3-Sl-HhTdfl2fzFy1AOcHKP7qg&noncestr=Wm3WZYTPz0wzccnW&timestamp=1414587457&url=http://mp.weixin.qq.com?params=value");

        // SHA1签名生成
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(str.getBytes());
        byte[] digest = md.digest();

        StringBuffer hexstr = new StringBuffer();
        String shaHex = "";
        for (int i = 0; i < digest.length; i++)
        {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2)
            {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }

        String str2 = hexstr.toString();
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str2.equals(str1));
    }
}
