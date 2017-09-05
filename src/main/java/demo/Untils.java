package demo;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.dom4j.DocumentException;

import javax.naming.AuthenticationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Untils {

    private Untils() {
    }


    private static Untils untils = null;


    //静态工厂方法
    public static Untils getInstance() {
        if (untils == null) {
            untils = new Untils();
        }
        return untils;
    }


    public String invokeGetMethod(String auth, String url) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").get(ClientResponse.class);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
        return response.getEntity(String.class);
    }


    /**
     * <p>方法描述: 进行jira认证时需使用的方法，为了查看httpCode</p>
     * <p>方法备注: </p>
     *
     * @param auth
     * @param url
     * @return
     * @throws ClientHandlerException <p>创建人：王东辉</p>
     *                                <p>创建时间：2017年7月21日 下午3:03:48</p>
     */
    public int invokeGetMethodForAuth(String auth, String url) throws ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").get(ClientResponse.class);
        int statusCode = response.getStatus();
        return statusCode;
    }


    public String invokePostMethod(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").post(ClientResponse.class, data);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
        return response.getEntity(String.class);
    }


    /**
     * <p>方法描述: 添加一个返回ClientResponse的POST方法，好根据code和内容综合判断</p>
     * <p>方法备注: </p>
     *
     * @param auth 认证信息
     * @param url  请求地址
     * @param data JSON参数
     * @return <p>创建人：王东辉</p>
     * <p>创建时间：2017年7月31日 上午11:47:22</p>
     */
    public ClientResponse invokePostMethodForZephyr(String auth, String url, String data) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").post(ClientResponse.class, data);
        return response;
    }


    /**
     * <p>方法描述: 根据原始post方法修改的，用以适用与更新issue的工作流状态</p>
     * <p>方法备注: </p>
     *
     * @param auth 认证信息
     * @param url  Jira API接口
     * @param data 更新信息
     * @return 返回请求状态码
     * <p>创建人：王东辉</p>
     * <p>创建时间：2017年5月17日 下午2:23:39</p>
     */
    public int invokePostMethodForJiraTransition(String auth, String url, String data) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").post(ClientResponse.class, data);
        return response.getStatus();
    }


    public int invokePutMethod(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").put(ClientResponse.class, data);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
        return statusCode;
    }


    /**
     * <p>方法描述: 更新测试循环所使用的put请求方法</p>
     * <p>方法备注: </p>
     *
     * @param auth
     * @param url
     * @param data
     * @return
     * @throws AuthenticationException
     * @throws ClientHandlerException  <p>创建人：王东辉</p>
     *                                 <p>创建时间：2017年7月27日 下午2:40:59</p>
     */
    public String invokePutMethodForJira(String auth, String url, String data) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").put(ClientResponse.class, data);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
        return response.getEntity(String.class);
    }


    public void invokeDeleteMethod(String auth, String url) throws AuthenticationException, ClientHandlerException {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").delete(ClientResponse.class);
        int statusCode = response.getStatus();
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
    }


    /**
     * <p>方法描述: 修改以适用于Jira删除附件</p>
     * <p>方法备注: </p>
     *
     * @param auth 认证信息
     * @param url  接口url
     * @return 返回httpCode
     * @throws AuthenticationException
     * @throws ClientHandlerException  <p>创建人：王东辉</p>
     *                                 <p>创建时间：2017年5月19日 上午10:13:28</p>
     */
    public int invokeDeleteMethodForJira(String auth, String url) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").delete(ClientResponse.class);
        return response.getStatus();
    }


    /**
     * <p>方法描述: 根据Jira插件的使用需要，添加返回值类型为String的DELETE方法</p>
     * <p>方法备注: </p>
     *
     * @param auth
     * @param url
     * @return <p>创建人：王东辉</p>
     * <p>创建时间：2017年7月31日 下午4:44:49</p>
     */
    public String invokeDeleteMethodForZephyr(String auth, String url) {
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Authorization", "Basic " + auth).type("application/json")
                .accept("application/json").delete(ClientResponse.class);
        return response.getEntity(String.class);
    }


    public String getTestResult(String xmlpath) throws DocumentException {
        String context = null;
        // SAXReader reader = new SAXReader();
        //Document document = reader.read(new File(xmlpath));
        //Element node = document.getRootElement();
/*				int failed= Integer.valueOf(node.attribute("failed").getText());
            int total= Integer.valueOf(node.attribute("total").getText());
			int passed= Integer.valueOf(node.attribute("passed").getText());
			int skipped= Integer.valueOf(node.attribute("skipped").getText());*/
        int failed = 0;
        int total = 0;
        int passed = 0;
        int skipped = 0;
        if (failed > 0) {
            context = issueContext(failed, total, passed, skipped);
        }
        return context;
    }


    private String issueContext(int failed, int total, int passed, int skipped) {
        String title = " AUTOMATION TEST RESULT FAILURES ";
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String timedate = format.format(date);
        String summary = timedate + title + failed;
        String description = timedate + " 自动化测试结果 " + "用例总数为 " + total + " 成功通过用例 " + passed + " 失败用例 " + failed + " 跳过用例 " + skipped +
                "详情请查看测试报告";
        return summary + "&" + description;

    }


    public static void main(String[] args) throws AuthenticationException {
        String url = "http://jira.paas.sinopec.com/rest/api/latest/project/N000H00700CTS";
        Client client = Client.create();
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.header("Cookie", "crowd.token_key=uvYz0nKM2GEOlorJW00veQ00").type("application/json")
                .accept("application/json").get(ClientResponse.class);
        int statusCode = response.getStatus();
        System.out.println(statusCode);
        if (statusCode == 401) {
            throw new AuthenticationException("Invalid Username or Password");
        }
        System.out.println(response.getEntity(String.class));
//        try(BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntityInputStream()))) {
//            String line = null;
//            while((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
