import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * description： <br>
 * createTime: 2017/12/717:49 <br>
 *
 * @author zzh
 */
public class Test {

    public static void main(String[] args) {
        if(args.length < 1) {
            return;
        }
        String urlStr = args[0];
        try(BufferedReader bufr = new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL(urlStr)
                .openStream()), "utf-8"));) {
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = bufr.readLine()) != null) {
                sb.append(line + "\n");
            }
            bufr.close();
            System.out.println(sb.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
