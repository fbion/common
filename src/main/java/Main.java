package Script;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * description： <br>
 * createTime: 2017/12/717:20 <br>
 *
 * @author zzh
 */
public class Main {

    public static void main(String[] args) {
        String start = "0";
        String q = "java";
        if(args.length > 1) {
            start = args[0];
            q = args[1];
        }
        //&gss=.com&sig=432dd570d1a386253361f581254f9ca1  element
        String urlStr = "https://www.googleapis.com/customsearch/v1?key=AIzaSyCVAXiUzRYsML1Pv6RwSG1gunmMikTzQqY&rsz=filtered_cse&num=10&hl=zh_CN&prettyPrint=false&source=gcsc&start=" + start + "&cx=002888546953948157320:bahxnr-mq7i&q=" + q + "&sort=&googlehost=www.google.com";
        try(BufferedReader bufr = new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL(urlStr)
                .openStream()), "utf-8"));) {
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = bufr.readLine()) != null) {
                sb.append(line);
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
