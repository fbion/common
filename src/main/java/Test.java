import java.io.File;

/**
 * description： <br>
 * createTime: 2017/12/717:49 <br>
 *
 * @author zzh
 */
public class Test {

    public static void main(String[] args) {
       String filePath = "e:\\BK";
       File file = new File(filePath);
        System.out.println(file.length());
    }
}
