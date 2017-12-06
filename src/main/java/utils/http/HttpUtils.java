package utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpTrace;
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

    private static CloseableHttpClient httpClient = getHttpClient();


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
     *
     * @param url         回调地址
     * @param requestBody 回调信息
     * @return 返回post状态码
     * @throws UnsupportedEncodingException <p>注释创建人：王东辉</p>
     *                                      <p>创建时间：2017年4月25日 下午2:04:14</p>
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
     *
     * @param url         post地址
     * @param requestBody 信息JSON串
     * @return 返回post结果状态码
     * @throws UnsupportedEncodingException <p>创建人：王东辉</p>
     *                                      <p>创建时间：2017年4月25日 下午2:02:07</p>
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
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);

                int resultCode = response.getStatusLine().getStatusCode();
                response.close();
                return resultCode;
            }
        } catch (ClientProtocolException e) {
            for (StackTraceElement element : e.getStackTrace()) {
                logger.error(element.toString());
            }
        } catch (IOException e) {
            for (StackTraceElement element : e.getStackTrace()) {
                logger.error(element.toString());
            }
        }
        return -1;
    }


    public static CloseableHttpResponse httpgetRequest(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        return httpClient.execute(httpGet);
    }


    public static void testTraceRequest(String url) throws IOException {
        HttpTrace httpTrace = new HttpTrace(url);
        CloseableHttpResponse response = httpClient.execute(httpTrace);
        System.out.println("Status Code : " + response.getStatusLine());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) throws IOException {
         testTraceRequest("http://10.248.14.203:8080/");
        System.out.println("-------------------------------------------");
        testTraceRequest("http://10.248.14.203:9000/");
    }
}
