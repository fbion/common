package utils.http;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
public class HttpUtil {

    /**
     * 添加系统日志
     */
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    private static PoolingHttpClientConnectionManager cm;

    private static CloseableHttpClient httpClient;

    public static final ContentType CONTENT_TYPE_HTML = ContentType.create("text/html", "UTF-8");

    public static final ContentType CONTENT_TYPE_JSON = ContentType.create("application/json", "UTF-8");

    public static final ContentType CONTENT_TYPE_XML = ContentType.create("text/xml", "UTF-8");


    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(10000).setConnectionRequestTimeout(5000)
            .setSocketTimeout(5000).build();

    static {
        httpClient = getHttpClient();
    }

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
        httpPost.setConfig(requestConfig);
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
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(new StringEntity(requestBody, "UTF-8"));
        httpPost.addHeader("Content-Type", "application/json");
        return getResult(httpPost);
    }


    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private static int getResult(HttpRequestBase request) {
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

    public static ResonseEntity doGetRequest(String url) throws IOException {
        return doGetRequest(url, new Header[]{});
    }

    public static ResonseEntity doGetRequest(String url, Header[] headers) throws IOException {
        logger.info("request url : " + url);
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeaders(headers);
        return assambleResonseEntity(httpClient.execute(httpGet));
    }


    public static ResonseEntity doPostRequest(String url, String postData) throws
            IOException {
        return doPostRequest(url, new Header[]{}, postData);
    }


    public static ResonseEntity doPostRequest(String url, Header[] headers, String postData) throws
            IOException {
        return doPostRequest(url, headers, postData, CONTENT_TYPE_HTML);
    }

    public static ResonseEntity doPostRequest(String url, Header[] headers, String postData,
                                                      ContentType contentType) throws IOException {
        logger.debug("request url : " + url);
        logger.debug("request postData : " + postData);
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeaders(headers);
        HttpEntity entity = (contentType == null ?  new StringEntity(postData) :
                new StringEntity(postData,contentType));
        httpPost.setEntity(entity);

        return assambleResonseEntity(httpClient.execute(httpPost));
    }

    public static ResonseEntity assambleResonseEntity(CloseableHttpResponse response) {
        ResonseEntity entity = new ResonseEntity();
        entity.setCode(response.getStatusLine().getStatusCode());
        try {
            entity.setResponseBody(EntityUtils.toString(response.getEntity(), Consts.UTF_8));
            logger.debug("responseBody : " + entity.getResponseBody());
        } catch (IOException e) {
            logger.error("获取请求体异常");
            entity.setCode(500);
            entity.setResponseBody("");
        }
        return entity;
    }

    public static class ResonseEntity {
        private int code;

        private String responseBody;


        public int getCode() {
            return code;
        }


        public String getResponseBody() {
            return responseBody;
        }


        public ResonseEntity setCode(int code) {
            this.code = code;
            return this;
        }


        public ResonseEntity setResponseBody(String responseBody) {
            this.responseBody = responseBody;
            return this;
        }
    }


    public static void main(String[] args) throws IOException {
        ResonseEntity responseEntity = doGetRequest("https://cts.paas.sinopec.com/cts/index.jsp",
                new Header[]{new BasicHeader("JSESSIONID", "FCA4CB40FE8479562A5B8F0E72F0930E"),
                new BasicHeader("pcitc_sso_token", "xCxATVyxpZzV7JxlfXPe8QIyJXl9QjlfSOoXd3esqjrGzPobSGcAvZaMfe%2BxjbVSiQ9pIyeNTr0%3D")});
        System.out.println(responseEntity.getCode());
        System.out.println(responseEntity.getResponseBody());
    }
}
