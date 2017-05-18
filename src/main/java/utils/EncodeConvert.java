package utils;

/**
 * Created by Administrator on 2016/5/10.
 */
public class EncodeConvert {

    public static void main(String[] args) {
        int str  = 0xCAFEBABE;
        byte[] bytes = new byte[4];
        for(int i=0;i<4;i++){
            bytes[i]=(byte)(str>>8*(3-i) & 0xFF);
//            System.out.print(Integer.toBinaryString(bytes[i])+" ");
            System.out.print((bytes[i])+" ");
        }

        String s = "漱壕";
    }
}
