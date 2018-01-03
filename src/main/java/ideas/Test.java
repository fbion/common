package ideas;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.http.https.HttpUtil;

import java.io.IOException;

/**
 * description： <br>
 * createTime: 2017/12/118:53 <br>
 *
 * @author zzh
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String url = "https://bwh1.net/vps-hosting.php";
        HttpUtil.ResponseEntity responseEntity = HttpUtil.doGetRequest(url);
        Document document = Jsoup.parse(responseEntity.getResponseBody());
        Element pv10 = document.getElementsByClass("bronze").first();
        if(pv10.child(2).child(0).text().indexOf("no stock") < 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
