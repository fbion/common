package test.code;

import java.io.UnsupportedEncodingException;

public class TestTransformation {

	public static boolean isEmpty(String str) {
		if(str != null) {
			return ("").equals(str);
		}
		return false;
	}
	
	/** 
     * <把字符串转换成字节数组然后在封装成字符串> 
     * <功能详细描述> 
     * @param chinese 
     * @return 
     * @see [类、类#方法、类#成员] 
     */  
    public static String chineseToString(String chinese)  
    {  
        if (isEmpty(chinese))  
        {  
            return "";  
        }  
        else  
        {  
            // 定义StringBuffer  
            StringBuffer sb = new StringBuffer();  
              
            // 把传进来的字符串转换成字节数组  
            byte[] b = chinese.getBytes();  
              
            byte[] temp = null;  
              
            // 遍历字节数组，把字节数组转换成字符串  
            for (int i = 0; i < b.length; i++)  
            {  
                temp = new byte[4];  
                temp[0] = b[i];  
                temp[1] = 0;  
                temp[2] = 0;  
                temp[3] = 0;  
                sb.append(lBytesToInt(temp));  
                if (i < b.length - 1)  
                {  
                    sb.append("@");  
                }  
            }  
              
            return sb.toString();  
        }  
    }  
      
    /** 
     * <把字节数组封装成的字符串转换成原来的字符串> 
     * <功能详细描述> 
     * @param stc 
     * @return 
     * @see [类、类#方法、类#成员] 
     */  
    public static String stringToChinese(String stc)  
    {  
        // 如果传递的字符串为空则直接返回空  
        if (isEmpty(stc))  
        {  
            return "";  
        }  
        else  
        {  
            // 分割字符串  
            String[] s = stc.split("@");  
            if (s.length > 0)  
            {  
                // 循环构造BYTE数组  
                byte[] b = new byte[s.length];  
                for (int i = 0; i < s.length; i++)  
                {  
                    b[i] = (byte)Integer.parseInt(s[i]);  
                }  
                  
                // 根据BYTE数组构造字符串  
                return new String(b);  
            }  
            else  
            {  
                return "";  
            }  
        }  
    }  
      
    /** 
     * 将低字节数组转换为int 
     * @param b byte[] 
     * @return int 
     */  
    public static int lBytesToInt(byte[] b)  
    {  
        int s = 0;  
        for (int i = 0; i < 3; i++)  
        {  
            if (b[3 - i] >= 0)  
            {  
                s = s + b[3 - i];  
            }  
            else  
            {  
                s = s + 256 + b[3 - i];  
            }  
            s = s * 256;  
        }  
        if (b[0] >= 0)  
        {  
            s = s + b[0];  
        }  
        else  
        {  
            s = s + 256 + b[0];  
        }  
        return s;  
    }  
    
    public static byte[] HexString2bytes(String src, String regex) {
    	String[] temp = src.split(regex);
    	byte[] bytes = new byte[temp.length];
    	int i = 0;
    	for (String string : temp) {
			bytes[i++] = (byte)Integer.parseInt((string),16);
		}
    	return bytes;
    } 
    
    public static String string2HexString(String src) {
    	StringBuilder sb = new StringBuilder();
    	byte[] b1;
    	try {
    		b1 = src.getBytes("UTF-8");
    	}catch(UnsupportedEncodingException e) {
    		e.printStackTrace();
    		return null;
    	}
    	for (byte b : b1) {
    		sb.append(Integer.toHexString((b&0xFF))+" ");
		}
    	return sb.toString();
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
    	byte[] test = new byte[] { 5, 0, 3, 85, 3, 2, 109, 65, -111, -49, 93, -14, 79, 127, 117,
                40, 0, 49, 0, 48, 0, 48, 0, 48, 0, 46, 0, 48, 0, 48, 0, 77, 0, 66, -1, 12, 82, 105,
                79, 89, 0, 48, 0, 46, 0, 48, 0, 48, 0, 77, 0, 66, -1, 27, -1, 8, 87, 71, 78, 13,
                84, 43, 83, 74, 94, 116, 83, 5, 48, 1, 91, -102, 84, 17, 109, 65, -111, -49, 83, 5,
                -1, 9, 48, 2, -115, -123, 81, -6, 109, 65, -111, -49, 0, 48, 0, 46, 0, 48, 0, 48,
                0, 77, 0, 66, -1, 12, -115, -123, 81, -6, 109, 65, -111, -49, -1, 8, 78, 13, 84,
                43, 110, 47, 111, -77, 83, -16, -1, 9, 99, 9, 113, 103, 126, -90, 91, -102, -115,
                68, -115, 57, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0 };
    	String s2 = "43 48 20 2a 20 48 54 54  50 2f 31 2e 31 0d 0a 48 4f 53 54 3a 20 32 33 39  2e 32 35 35 2e 32 35 35 2e 32 35 30 3a 31 39 30  30 0d 0a 4d 41 4e 3a 20 22 73 73 64 70 3a 64 69  73 63 6f 76 65 72 22 0d 0a 4d 58 3a 20 36 0d 0a  53 54 3a 20 75 72 6e 3a 73 63 68 65 6d 61 73 2d  75 70 6e 70 2d 6f 72 67";
//    	System.out.println(new String(test,"gb2312"));
//    	System.out.println(new String(test,"GBK"));
//    	System.out.println(new String(test,"UTF-16"));
    	String[] charset = new String[]{"GB2312","GBK","UTF-8","UTF-16"};
    	String str = "让我们荡起双桨，小船儿推开波浪。小船儿轻轻漂荡在水中，迎来了凉爽的风。";
    	byte[] bytes1 = HexString2bytes(string2HexString(str)," ");
    	byte[] bytes3 = str.getBytes(charset[3]);
    	for(int i = 0; i < (bytes1.length < bytes3.length ? bytes1.length : bytes3.length); i++) {
    		if(bytes1[i] != bytes3[i]) {
    			System.out.println("Not Equals");
    		}
    	}
    	if( bytes1 != null) {
    		System.out.println(new String(bytes1,charset[2]));
    	}
    	
    	System.out.println(new String(bytes3,charset[3]));
    	
    	byte[] bytes2 = HexString2bytes(s2.toString()," +");
    	System.out.println(new String(bytes2,charset[2]));
	}
}
