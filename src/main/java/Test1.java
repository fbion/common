import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * description: <br>
 * createTime: 2018/4/2711:52 <br>
 *
 * @author zzh
 */
public class Test1 {

    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = "\\x"+Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static void readFileToHexString(String filePath) {
        try(FileInputStream in = new FileInputStream(filePath)) {
            byte[] bytes = new byte[(int) new File(filePath).length()];
            in.read(bytes);
            System.out.println(bytesToHexString(bytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void compare2FilesInBanary(String srcFiile, String desFile) {
        try(FileInputStream srcIn = new FileInputStream(srcFiile);
            FileInputStream desIn = new FileInputStream(desFile)) {
            long srcLength = srcIn.available();
            long desLength = desIn.available();
            System.out.println(srcLength);
            System.out.println(desLength);
            byte[] bytesSrc = new byte[(int) srcLength];
            byte[] bytesDes = new byte[(int) desLength];
            if(bytesSrc.length == bytesDes.length) {
                srcIn.read(bytesSrc);
                desIn.read(bytesDes);
                for (int i = 0; i < bytesSrc.length; i++) {
                    if(bytesSrc[i] != bytesDes[i]) {
                        System.out.println("not euqals");
                        System.exit(1);
                    }
                }
                System.out.println("euqals");
            } else{
                System.out.println("not euqals");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFileToHexString("E:\\IDEA\\CTS2.0\\dev-help\\cloud.cer");
        compare2FilesInBanary("E:\\IDEA\\CTS2.0\\dev-help\\cloud.cer", "E:\\new.cer");
    }
}
