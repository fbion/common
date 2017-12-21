package test;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.StringReader;

/**
 * description: <br>
 * createTime: 2017/10/3111:08 <br>
 *
 * @author zzh
 */
public class TestJson {

    public static void test1() throws IOException {
        JSONObject json = JSONObject.fromObject("{\"Response\":{\"NodeId\":[\"00150304898551876969141877486dae\"]," +
                "\"RelatedQuestions\":[{\"Question\":[\"上海核心套系\"]}],\"Type\":[\"1\"],\"Commands\":[{\"Command\":[{\"Arg\":[\"http://poc.cluster.xiaoi.com/robot/app/hgdspoc/imgmsgData/ffd63571c0314\\t\\t\\t\\t6298ebc47874c50213d/articles.xml\",\"UTF-8\",{\"Articles\":[{\"item\":[{\"Url\":[\"imgmsgData/ffd63571c03146298ebc47874c50213d/index_1.html?t=1503652131661\"],\"PicUrl\":[\"imgmsgData/ffd63571c03146298ebc47874c50213d/imgmsgItem1.png?t=1503308781500\"],\"Title\":[\"哈根达斯上海提取点\"]}]}]}]}]}],\"Similarity\":[\"1.0\"],\"ModuleId\":[\"core\"]}}");
//        System.out.println(json.getJSONObject("Response").getJSONArray("Commands").getJSONObject(0).getJSONArray("Command").getJSONObject(0).getJSONArray("Arg"));
        JSON.parse("{\"Response\":{\"NodeId\":[\"00150304898551876969141877486dae\"]," +
                "\"RelatedQuestions\":[{\"Question\":[\"上海核心套系\"]}],\"Type\":[\"1\"]," +
                "\"Commands\":[{\"Command\":[{\"Arg\":[\"http://poc.cluster.xiaoi.com/robot/app/hgdspoc/imgmsgData/ffd63571c0314\\t\\t\\t\\t6298ebc47874c50213d/articles.xml\",\"UTF-8\",{\"Articles\":[{\"item\":[{\"Url\":[\"imgmsgData/ffd63571c03146298ebc47874c50213d/index_1.html?t=1503652131661\"],\"PicUrl\":[\"imgmsgData/ffd63571c03146298ebc47874c50213d/imgmsgItem1.png?t=1503308781500\"],\"Title\":[\"哈根达斯上海提取点\"]}]}]}]}]}],\"Similarity\":[\"1.0\"],\"ModuleId\":[\"core\"]}}");
        JsonNode node = new ObjectMapper().readTree
                ("{\"Response\":{\"NodeId\":[\"00150304898551876969141877486dae\"]," +
                        "\"RelatedQuestions\":[{\"Question\":[\"上海核心套系\"]}],\"Type\":[\"1\"]," +
                        "\"Commands\":[{\"Command\":[{\"Arg\":[\"http://poc.cluster.xiaoi.com/robot/app/hgdspoc/imgmsgData/ffd63571c0314\\t\\t\\t\\t6298ebc47874c50213d/articles.xml\",\"UTF-8\",{\"Articles\":[{\"item\":[{\"Url\":[\"imgmsgData/ffd63571c03146298ebc47874c50213d/index_1.html?t=1503652131661\"],\"PicUrl\":[\"imgmsgData/ffd63571c03146298ebc47874c50213d/imgmsgItem1.png?t=1503308781500\"],\"Title\":[\"哈根达斯上海提取点\"]}]}]}]}]}],\"Similarity\":[\"1.0\"],\"ModuleId\":[\"core\"]}}");
        System.out.println(node.isObject());
    }


    public static void test2() throws ParsingException, IOException {
        String xmlStr = "<Response>\n" +
                "    <Type>1</Type>\n" +
                "    <NodeId>00150304898551876969141877486dae</NodeId>\n" +
                "    <ModuleId>core</ModuleId>\n" +
                "    <Similarity>1.0</Similarity>\n" +
                "    <Commands class=\"array\">\n" +
                "        <Command name=\"imgtxtmsg\" state=\"1\">\n" +
                "            <Arg>\n" +
                "                http://poc.cluster.xiaoi.com/robot/app/hgdspoc/imgmsgData/ffd63571c0314\n" +
                "                6298ebc47874c50213d/articles.xml\n" +
                "            </Arg>\n" +
                "            <Arg/>\n" +
                "            <Arg>UTF-8</Arg>\n" +
                "            <Arg>\n" +
                "                <Articles>\n" +
                "                    <item>\n" +
                "                        <Title><![CDATA[哈根达斯上海提取点]]></Title>\n" +
                "                        <Description><![CDATA[]]></Description>\n" +
                "                        <PicUrl><![CDATA[http://poc.cluster.xiaoi.com/robot/app/hgdspoc/imgmsgData/ffd63571c03146298ebc47874c50213d/imgmsgItem1.png?t=1503308781500]]></PicUrl>\n" +
                "                        <Url><![CDATA[http://poc.cluster.xiaoi.com/robot/app/hgdspoc/imgmsgData/ffd63571c03146298ebc47874c50213d/index_1.html?t=1503308816723]]>\n" +
                "                        </Url>\n" +
                "                    </item>\n" +
                "                </Articles>\n" +
                "            </Arg>\n" +
                "        </Command>\n" +
                "    </Commands>\n" +
                "    <RelatedQuestions>\n" +
                "        <Question>上海核心套系</Question>\n" +
                "    </RelatedQuestions>\n" +
                "</Response>";
        XMLSerializer serializer = new XMLSerializer();
        String result = serializer.read(xmlStr).toString();
        System.out.println(result);
//        System.out.println(result.replaceAll("(\"dependencies\":)\\{(.*?)\\},", "$1[{$2}],"));

        Document doc = (new Builder()).build(new StringReader(xmlStr));
        Element root = doc.getRootElement();
//        for (int i = 0; i < root.getNamespaceDeclarationCount(); i++) {
//            String prefix = root.getNamespacePrefix(i);
//            System.out.println(prefix);
//            String uri = root.getNamespaceURI(prefix);
//            System.out.println(uri);
//            if (!StringUtils.isBlank(uri)) {
//                System.out.println(false);
//            }
//        }
        System.out.println(root.getChildElements().get(4).getAttribute("class").getValue());
        Element element = root.getChildElements().get(4);
        String prefix = element.getNamespacePrefix(0);
        System.out.println(prefix);
        String uri = element.getNamespaceURI(prefix);
        if (!StringUtils.isBlank(uri)) {
            System.out.println(false);
        }
    }


    public static void main(String[] args) throws IOException, ParsingException {
        test2();
    }
}
