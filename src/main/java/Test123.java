import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * description： <br>
 * createTime: 2018/4/1819:00 <br>
 *
 * @author zzh
 */
public class Test123 {

    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new FileReader("E:\\zzh\\codes\\own\\common\\src\\main\\java\\advance\\JVM\\oom\\TestStackOverFlow.java"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\zzh\\codes\\own\\common\\src\\main\\java\\advance\\JVM\\oom\\TestStackOverFlow1.java"))) {
            String line = null;
            while((line = br.readLine()) != null) {
                bw.write(line + "\r\n");
                bw.flush();
                System.out.println(line);
                if(line.indexOf("main") >= 0) {
                    System.out.println("loop");
                    for (int i = 0; i < 32768; i++) {
                        bw.write("        String s" + i + " = \"\";\r\n");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
