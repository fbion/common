package work.qizhi.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Created by Administrator on 2016/6/25.
 */
public class TestJsoup {
    public static void main(String[] args) {
        String str = "<div class=\"note note-info note-shadow\">\n" +
                "                <p>\n" +
                "                    如果因微信授权问题无法接入，您可使用下面的地址配置到微信自定义菜单，实现微信端接入机器人：<a href=\"http://cloud.smartnlp.cn/cloud/robot/webui/55d28d61d3a93df500131c24\">\n" +
                "                    http://cloud.smartnlp.cn/cloud/robot/webui/55d28d61d3a93df500131c24</a>\n" +
                "                </p>\n" +
                "            </div>" +
                "<div style=\"margin-top:40px ; margin-bottom:10px ; margin-left:0px ;\">\n" +
                "                <a href=\"http://weixin.smartnlp.cn/?appKey=55d28d61d3a93df500131c24\" target=\"_blank\" class=\"btn btn-bg blue\" style=\"margin-left:0px ; font-size:18px\" onclick=\"javascript:$('#bushu')[0].click();\"><i class=\"fa fa-link\"></i>\n" +
                "                    绑定公众号 </a>\n" +
                "                <a id=\"bushu\" style=\"display:none\" data-target=\"#bushu-check\" data-toggle=\"modal\"></a>\n" +
                "            </div>";
        Document doc = Jsoup.parse(str);
        Elements elements = doc.getElementsByAttributeValue("class", "note note-info note-shadow");
        System.out.println(elements);
        System.out.println(elements.first());
    }
}
