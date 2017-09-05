package utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 项目名称：SINOPEC-CTS<br/>
 * *********************************<br/>
 * <p>类名称：HttpUtils</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/6 9:51<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/6 9:51<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class HttpUtils {

    /**
     * 添加系统日志
     */
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";
    private static String UTF_8 = "UTF-8";

    private static void init() {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);// 整个连接池最大连接数
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    
    /**
     * <p>方法描述: CTS对外接口回调post方法</p>
     * <p>方法备注: </p>
     * @param url 回调地址
     * @param requestBody 回调信息
     * @return 返回post状态码
     * @throws UnsupportedEncodingException
     * <p>注释创建人：王东辉</p>
     * <p>创建时间：2017年4月25日 下午2:04:14</p>
     */
    public static int httpPostRequest(String url, String requestBody)
            throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(requestBody, "UTF-8"));
//        Header[] headers = httpPost.getAllHeaders();
        httpPost.addHeader("Content-Type", "text/html");
        return getResult(httpPost);
    }

    
    /**
     * <p>方法描述: CTS向云信推送消息的方法</p>
     * <p>方法备注: url固定，为http://chatwork.pcitc.com/hooks/8mikjmLtRjBPaGZm2/yYaJatKmDjBiYQK3ygLRtKNHESYn2aDctYXq5R75NjNXPZrT</p>
     * @param url post地址
     * @param requestBody 信息JSON串
     * @return 返回post结果状态码
     * @throws UnsupportedEncodingException
     * <p>创建人：王东辉</p>
     * <p>创建时间：2017年4月25日 下午2:02:07</p>
     */
    public static int httpPostJSON(String url, String requestBody)
            throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(requestBody, "UTF-8"));
        httpPost.addHeader("Content-Type", "application/json");
        return getResult(httpPost);
    }

    
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }

        return pairs;
    }


//    /**
//     * 处理Http请求
//     *
//     * @param request
//     * @return
//     */
//    private static int getResult(HttpRequestBase request) {
//        // CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpClient httpClient = getHttpClient();
//        try {
//            CloseableHttpResponse response = httpClient.execute(request);
//            // response.getStatusLine().getStatusCode();
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                // long len = entity.getContentLength();// -1 表示长度未知
//                String result = EntityUtils.toString(entity);
//                response.close();
//                // httpClient.close();
//                return result;
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//
//        }
//
//        return EMPTY_STR;
//    }


    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private static int getResult(HttpRequestBase request) {
        // CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);

                int resultCode =  response.getStatusLine().getStatusCode();
                response.close();
                return resultCode;
            }
        } catch (ClientProtocolException e) {
            for (StackTraceElement element: e.getStackTrace()){
                logger.error(element.toString());
            }
        } catch (IOException e) {
            for (StackTraceElement element: e.getStackTrace()){
                logger.error(element.toString());
            }
        }
        return -1;
    }

    public static CloseableHttpResponse httpgetRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = getHttpClient();
        return httpClient.execute(httpGet);
    }


    public static void main(String[] args) throws IOException {
        String url = "http://jira.paas.sinopec.com/rest/api/latest/project/N000H00700CTS";
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpClient = getHttpClient();
//        httpGet.addHeader("Cookie","JSESSIONID=C10D8711A4724938E5EC21444B91259C; pcitc_sso_token=xCxATVyxpZzV7JxlfXPe8QIyJXl9QjlfSOoXd3esqjpWSqf4WZn15vx5ieMdcF%2FYt0etPU0pzgY%3D; adfs_sso_token=eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ik1rWFByOGRMaHNJMkNkSWtVRDE3SWtzUy01MCJ9.eyJhdWQiOiJodHRwOi8vcHJpdmF0ZWNsb3Vkc2VydmljZSIsImlzcyI6Imh0dHA6Ly9hZGZzLmNsb3VkLnNpbm9wZWMuY29tL2FkZnMvc2VydmljZXMvdHJ1c3QiLCJpYXQiOjE1MDQ1NzM1MzksImV4cCI6MTUwNDY1OTkzOSwiY29tbW9ubmFtZSI6IuWRqOW_l-i-iSIsInVuaXF1ZV9uYW1lIjoiemhpaHVpLnpob3UiLCJ1cG4iOiJ6aGlodWkuemhvdUBzaW5vcGVjLmNvbSIsImVtYWlsIjoiemhpaHVpLnpob3VAcGNpdGMuY29tIiwiYXV0aF90aW1lIjoiMjAxNy0wOS0wNVQwMTowNTozOS41OThaIiwiYXV0aG1ldGhvZCI6Imh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9hdXRoZW50aWNhdGlvbm1ldGhvZC9wYXNzd29yZCIsInZlciI6IjEuMCJ9.hjDsBQPdFIUl5MekwW-Kb9OqEI5Tas4kj4OF0YBdYzG3WgyuEKdEqxW8JIUHr_zRHfA-9CLrBntEwcfiG0mLsNYnZyYOs8Rl88YdmIXXsxuSW-CJNQt7tQz0eKKu2V8EpXye40RDsrzxtaIcuQTIymtiyKhr1oa9b2yfjr3SKse93nfFEE25Xy5dmEPIfV9x4LsNZRUAolbOQUKpKxrn8zNsyC0TScEqr8ZZJGyGkefpH0zYQnprMtwUl9UMwprQtTB6vu2wdiFwTMP7VZDbo9DLiOiKXFKQ7B1CfWaUZ6mhDyABboTnATW1yk5SlUDyn5f9w81nEM0enzlEqTQsfw; cdp_currentuser_info=%7B%22user%22%3A%7B%22createUserId%22%3A%22BF1B5F7735704BDF8155402AFD7C21B0%22%2C%22createUserName%22%3A%22%E7%8E%8B%E6%97%AD%E6%98%8E%22%2C%22createdDate%22%3A1488938279000%2C%22dataState%22%3A1%2C%22email%22%3A%22zhihui.zhou%40pcitc.com%22%2C%22memberId%22%3A%2221146AC330024AC58545B91DEFAFEACA%22%2C%22mobile%22%3A%22%22%2C%22revesedDate%22%3A1488938279000%2C%22reviseUserId%22%3A%22BF1B5F7735704BDF8155402AFD7C21B0%22%2C%22reviseUserName%22%3A%22%E7%8E%8B%E6%97%AD%E6%98%8E%22%2C%22userCode%22%3A%22zhihui.zhou%22%2C%22userId%22%3A%22zhihui.zhou%22%2C%22userName%22%3A%22%E5%91%A8%E5%BF%97%E8%BE%89%22%7D%7D; atlassian.xsrf.token=BBIJ-S00C-B2VH-18FC|3580f96bcc78baac22039dadaf2c7c0d53d1b5fb|lin; crowd.token_key=uvYz0nKM2GEOlorJW00veQ00");
        httpGet.addHeader("Cookie","crowd.token_key=uvYz0nKM2GEOlorJW00veQ00");

//        httpGet.addHeader();
        CloseableHttpResponse response = httpClient.execute(httpGet);
        System.out.println(response.getStatusLine().getStatusCode());
        try(BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
            String line = null;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
