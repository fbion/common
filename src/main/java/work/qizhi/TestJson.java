package work.qizhi;

import com.fasterxml.jackson.databind.JsonNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import play.libs.Json;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/24.
 */
public class TestJson {
    /**
     * Json中answers的键值
     */
    private static final String JSON_ANSWERS = "answers";

    /**
     * Json中respond的键值
     */
    private static final String JSON_RESPOND = "respond";

    /**
     * Json中suggestions的键值
     */
    private static final String JSON_SUGGESTIONS = "suggestions";

    /**
     * Json中choices的键值
     */
    private static final String JSON_CHOICES = "choices";

    public static void getAnswer(String str) {
        System.out.println("API string : " + str);
        String answer = "";
        JsonNode  tempNode= Json.parse(str);
        if(tempNode.has(JSON_ANSWERS) && tempNode.get(JSON_ANSWERS).size() > 0) {
            for (JsonNode node : tempNode.get(JSON_ANSWERS)) {
                String temp = node.get(JSON_RESPOND).asText();
                Document doc = Jsoup.parse(temp);
                for (Element img : doc.select("img")) {
                    String src = "\t查看图片请点击 ：" + img.attr("src") + "\t";
                    img.replaceWith(new TextNode(src, "https://coud.smartnlp.cn"));
                }
                for (Element br : doc.select("br")) {
                    br.replaceWith(new TextNode("\\n", "https://coud.smartnlp.cn"));
                }
                for (Element p : doc.select("p")) {
                    p.appendText("\\n");
                }
                for (Element element : doc.select("a")) {
                    element.replaceWith(new TextNode(element.toString().replaceAll("(<a href)(.*)(</a>)", "/a href$2/a"), "https://coud.smartnlp.cn"));
                }
                answer += doc.text().replaceAll("\\\\n( )?","\n").replaceAll("\\n$", "").replaceAll("(/a href)(.*)(/a)", "<a href$2</a>").replaceAll("http://weixin.smartnlp.cn/getWechatPic/\\?picUrl=", "");


                System.out.println("choices : " + node.get(JSON_CHOICES).asText());
                if(node.has(JSON_CHOICES) && node.get(JSON_CHOICES).size() > 0) {
                    answer += "\n";
                    char ch ='a';
                    for (JsonNode jsonNode : node.get(JSON_CHOICES)) {
                        answer += Character.toString(ch++) + "." + jsonNode.asText() + "  ";
                    }
                }

                System.out.println("answers : " + node.get(JSON_SUGGESTIONS).asText());
                if(node.has(JSON_SUGGESTIONS) && node.get(JSON_SUGGESTIONS).size() > 0) {
                    answer += "\n我猜您可能关心以下问题（回复序号）：";
                    int i = 1;
                    for (JsonNode n : node.get(JSON_SUGGESTIONS)) {
                        System.out.println(n.asText());
                        answer += "\n" + (i++) + "." + n.asText();
                    }
                }
                answer += "\n";
            }
        } else {
            answer = "抱歉，我也不知道呢";
        }
        System.out.println("answer : " + answer);
    }
    public static void main(String[] args) {
//        String str = "{\"errcode\":0,\"errmsg\":\"\",\"recordlist\":[],\"retcode\":0}";
//        JsonNode node = Json.parse(str);
//        node = node.get("recordlist");
//        System.out.println(node.size());
//        if("".equals(node.asText())) {
//            System.out.println("empty");
//        }

//        getAnswer("{\"question\":\"苹果手机带id\",\"answers\":[{\"respond\":\"<p><br/></p><p><br/></p><p><img alt=\\\"file\\\" src=\\\"https://res.smartnlp.cn/file/bd910770-4e13-44bf-9928-6229ef962bf1\\\" title=\\\"\\\"/></p>\",\"score\":70.62752499368946,\"choices\":[],\"suggestions\":[]}]}");
//        ObjectNode object = Json.newObject();
//        ObjectNode array = object.putObject("conversationCount");
//        ObjectNode tempObject = array.putObject(2016 + "");
//        ObjectNode tempObject1 = tempObject.putObject(21 + "");
//        tempObject1.put("customChatCount", 0).put("robotChatCount", 44);
//        tempObject1 = tempObject.putObject(22 + "");
//        tempObject1.put("customChatCount", 1).put("robotChatCount", 46);
//        System.out.println(object.toString());
//        Iterator<String> iterator = tempObject1.fieldNames();
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }

//        String str1 = "{\"question\":\"= ?\",\"answers\":[]}";
//        String str  = "{\n" +
//                "  \"question\": \"hi\", \n" +
//                "  \"answers\": [{\"respond\": \"你好\", \"score\": 0.1, \"choices\": [\"我要点餐\", \"我要叫车\"], \"suggestions\": [\"你好吗\", \"你最近好吗\"]}, {\"respond\": \"你在哪\", \"score\": 0.1, \"choices\": [\"我要点餐\", \"我要叫车\"], \"suggestions\": [\"你在哪里\", \"你的位置在哪\"]}]\n" +
//                "}";
//        JsonNode node = Json.parse(str);
//        System.out.println((node.get("answers").size()) + "end");
//
//        if(node.has("answers") && !"[]".equals(node.get("answers").toString())) {
//            System.out.println(node.get("answers").asText());
//        }
        String str = "[{&quot;_id&quot;:&quot;73fac4af924fb1f9765965fbbaee93f7&quot;,&quot;category&quot;:&quot;员工贷&quot;,&quot;subCategories&quot;:[&quot;查询&quot;,&quot;征信&quot;,&quot;员工贷未处理&quot;,&quot;申请失败&quot;,&quot;咨询电话&quot;,&quot;办理及其条件&quot;,&quot;取消&quot;,&quot;利息或手续费&quot;,&quot;审核&quot;,&quot;邮箱&quot;]},{&quot;_id&quot;:&quot;e7dea7c405682dbef5eeb0a8138b8f0a&quot;,&quot;category&quot;:&quot;客服&quot;,&quot;subCategories&quot;:[&quot;客服待分类&quot;,&quot;客服电话&quot;,&quot;反馈&quot;,&quot;在线咨询&quot;,&quot;人工客服&quot;]},{&quot;_id&quot;:&quot;362cdfdf1fb5f672e8f323896780b4b3&quot;,&quot;category&quot;:&quot;手机收款宝&quot;,&quot;subCategories&quot;:[&quot;绑定&quot;,&quot;业务咨询&quot;,&quot;征信&quot;,&quot;额度&quot;,&quot;收款宝待分类&quot;,&quot;信用卡&quot;,&quot;与其他业务关系&quot;,&quot;客服电话&quot;,&quot;利息或手续费&quot;,&quot;交易记录&quot;,&quot;审核&quot;,&quot;陈述&quot;]},{&quot;_id&quot;:&quot;fcec58739bd5f4721330fcb41a5e3bd4&quot;,&quot;category&quot;:&quot;易分期&quot;,&quot;subCategories&quot;:[&quot;征信&quot;,&quot;额度&quot;,&quot;红包抵扣&quot;,&quot;易分期待分类&quot;,&quot;还款&quot;,&quot;办理及其条件&quot;,&quot;办理过程中所遇问题&quot;,&quot;与其他业务关系&quot;,&quot;再借&quot;,&quot;利息或手续费&quot;,&quot;审核&quot;,&quot;到账&quot;,&quot;电话与回访&quot;]},{&quot;_id&quot;:&quot;b07e7e2ce2c594a5c953324094f0a551&quot;,&quot;category&quot;:&quot;替你还&quot;,&quot;subCategories&quot;:[&quot;额度&quot;,&quot;申请及条件&quot;,&quot;替你还待分类&quot;,&quot;多问题&quot;,&quot;二次申请&quot;,&quot;利息&quot;,&quot;还款&quot;,&quot;取消&quot;,&quot;与其他业务关系&quot;,&quot;红包&quot;,&quot;1问题不明确&quot;,&quot;到帐&quot;,&quot;审核&quot;,&quot;使用操作&quot;,&quot;基本信息&quot;]},{&quot;_id&quot;:&quot;f831148cdcd5e0d3f8ba1d6c31e87a8d&quot;,&quot;category&quot;:&quot;社区贷&quot;,&quot;subCategories&quot;:[&quot;查询&quot;,&quot;恢复通知&quot;,&quot;社区贷待分类&quot;,&quot;\\t审核&quot;,&quot;取消&quot;,&quot;特征及含义&quot;,&quot;申请及申请条件&quot;]},{&quot;_id&quot;:&quot;56a623ff95761797d81abfc368866ad9&quot;,&quot;category&quot;:&quot;第三方语料&quot;,&quot;subCategories&quot;:[&quot;公司&quot;,&quot;A_聊天库&quot;]}]";
        System.out.println(str.replaceAll(Pattern.quote("&quot;"), "\""));
        System.out.println(Json.toJson(str.replaceAll(Pattern.quote("&quot;"), "\"")));
    }
}
