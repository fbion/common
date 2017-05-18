package designpattern.decorator.ioexample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;

/**
 * Created by Administrator on 2016/5/6.
 */
public class InputTest {
    public static void main(String[] args) {
        String str = "<p>\n" +
                "    文字描述<img src=\"http://res.smartnlp.cn/file/586aa586-857a-4d28-b0fe-cadc0e8cf4ca\" title=\"\" alt=\"file\"/>\n" +
                "</p>" +
                "<p>\n" +
                "     文字描述<img src=\"http://res.smartnlp.cn/file/586aa586-857a-4d28-b0fe-cadc0e8cf4cb\" title=\"\" alt=\"file\"/>\n" +
                "                \"</p>";
        Document doc = Jsoup.parse(str);
        for (Element img : doc.select("img")) {
            String src = img.attr("src");
            img.replaceWith(new TextNode(src, "https://coud.smartnlp.cn"));
        }
        System.out.println(doc);
    }
}
