package utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by Administrator on 2016/7/22.
 */
public class CodeUtils {
    /**
     * 输出十进制，格式为&#00000; 如“中国”输出：&#20013;&#22269;
     *
     * @param s
     * @return
     */
    public static String string2UnicodeNumber(String s) {
        try {
            StringBuffer out = new StringBuffer("");
            StringBuffer temp = null;
            StringBuffer number = null;
            byte[] bytes = s.getBytes("unicode");
            for (int i = 2; i < bytes.length - 1; i += 2) {
                temp = new StringBuffer("&#");
                number = new StringBuffer("");
                String str = Integer.toHexString(bytes[i + 1] & 0xff);
                for (int j = str.length(); j < 2; j++) {
                    temp.append("0");
                }
                String str1 = Integer.toHexString(bytes[i] & 0xff);
                // 十进制转化为十六进制，结果为C8。
                // Integer.toHexString(200);
                // 十六进制转化为十进制，结果140。
                // Integer.parseInt("8C",16);
                number.append(str1);
                number.append(str);

                BigInteger bi = new BigInteger(number.toString(), 16);
                String show = bi.toString(10);

                temp.append(show + ";");
                out.append(temp.toString());
            }

            return out.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把中文转成Unicode码
     * @param str
     * @return
     */
    public static String chinese2Unicode(String str){
        String result="";
        for (int i = 0; i < str.length(); i++){
            int chr1 = (char) str.charAt(i);
            if(chr1>=19968&&chr1<=171941){//汉字范围 \u4e00-\u9fa5 (中文)
                result+="\\u" + Integer.toHexString(chr1);
            }else{
                result+=str.charAt(i);
            }
        }
        return result;
    }

    /**
     * 判断是否为中文字符
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 字符串转unicode
     * @param s
     * @return
     */
    public static String string2Unicode(String s) {
        try {
            StringBuffer out = new StringBuffer("");
            byte[] bytes = s.getBytes("unicode");
            for (int i = 2; i < bytes.length - 1; i += 2) {
                out.append("u");
                String str = Integer.toHexString(bytes[i + 1] & 0xff);
                for (int j = str.length(); j < 2; j++) {
                    out.append("0");
                }
                String str1 = Integer.toHexString(bytes[i] & 0xff);

                out.append(str);
                out.append(str1);
                out.append(" ");
            }
            return out.toString().toUpperCase();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * unicode转字符串
     * @param unicodeStr
     * @return
     */
    public static String unicode2String(String unicodeStr){
        StringBuffer sb = new StringBuffer();
        String str[] = unicodeStr.toUpperCase().split("[\\\\]?U");
        for(int i=0;i<str.length;i++){
            if("".equals(str[i])) {
                continue;
            }
            char c = (char)Integer.parseInt(str[i].trim(),16);
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "你好龍";
        System.out.println(chinese2Unicode(str));
        System.out.println(string2UnicodeNumber(str));
        System.out.println(str.getBytes().length);
        System.out.println(Arrays.toString(str.getBytes()));
    }
}
