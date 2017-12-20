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
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * 绕过验证
     *
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    private static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext ctx = SSLContext.getInstance("SSL");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().setSSLSocketFactory(socketFactory).build();
        return ctx;
    }

    public  static void ignoreCert() throws KeyManagementException, NoSuchAlgorithmException {
        //采用绕过验证的方式处理https请求
        SSLContext sslcontext = createIgnoreVerifySSL();

        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslcontext))
                .build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
//        HttpClients.custom().setConnectionManager(connManager);

        //创建自定义的httpclient对象
        httpClient = HttpClients.custom().setConnectionManager(connManager).
                setSSLHostnameVerifier((val1, val2) -> true).build();
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

    public static ResponseEntity doGetRequest(String url) throws IOException {
        return doGetRequest(url, new Header[]{});
    }

    public static ResponseEntity doGetRequest(String url, Header[] headers) throws IOException {
        logger.info("request url : " + url);
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeaders(headers);
        return assambleResponseEntity(httpClient.execute(httpGet));
    }


    public static ResponseEntity doPostRequest(String url, String postData) throws
            IOException {
        return doPostRequest(url, new Header[]{}, postData);
    }


    public static ResponseEntity doPostRequest(String url, Header[] headers, String postData) throws
            IOException {
        return doPostRequest(url, headers, postData, CONTENT_TYPE_HTML);
    }

    public static ResponseEntity doPostRequest(String url, Header[] headers, String postData,
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

        return assambleResponseEntity(httpClient.execute(httpPost));
    }


    public static ResponseEntity assambleResponseEntity(CloseableHttpResponse response) {
        ResponseEntity entity = new ResponseEntity();
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

    public static class ResponseEntity {
        private int code;

        private String responseBody;


        public int getCode() {
            return code;
        }


        public String getResponseBody() {
            return responseBody;
        }


        public ResponseEntity setCode(int code) {
            this.code = code;
            return this;
        }


        public ResponseEntity setResponseBody(String responseBody) {
            this.responseBody = responseBody;
            return this;
        }
    }


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException {
//        ignoreCert();
//        ResponseEntity ResponseEntity1 = doGetRequest("https://baidu.com");
//        System.out.println(ResponseEntity1.getResponseBody());
//        System.out.println("-----------------------------------------------------------------------------");
//
//        ResponseEntity responseEntity2 = doGetRequest("https://uat-jira.paas.sinopec.com/login.jsp");
//        System.out.println(responseEntity2.getResponseBody());

//        String adfsToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6Ind5VlZqUG9VbWFjQ3dHdFd1VzJIT2FYQWtwQSJ9.eyJhdWQiOiJodHRwOi8vcHJpdmF0ZWNsb3Vkc2VydmljZSIsImlzcyI6Imh0dHA6Ly9hZGZzLmNsb3VkLnNpbm9wZWMuY29tL2FkZnMvc2VydmljZXMvdHJ1c3QiLCJpYXQiOjE1MTM2NTQxNTIsImV4cCI6MTUxMzc0MDU1MiwiY29tbW9ubmFtZSI6IuWRqOW_l-i-iSIsInVuaXF1ZV9uYW1lIjoiemhpaHVpLnpob3UiLCJ1cG4iOiJ6aGlodWkuemhvdUBzaW5vcGVjLmNvbSIsImVtYWlsIjoiemhpaHVpLnpob3VAcGNpdGMuY29tIiwiYXV0aF90aW1lIjoiMjAxNy0xMi0xOVQwMzoyOToxMi4yNjhaIiwiYXV0aG1ldGhvZCI6Imh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9hdXRoZW50aWNhdGlvbm1ldGhvZC9wYXNzd29yZCIsInZlciI6IjEuMCJ9.g5yg1Hy46FpEvGy4Dmx6TSe-ZQx6EJXAKyg9adCGY5XtS4RUZkryTN0YZ7V0FCqeNyisDiLOtfuUuaI3HcSUqRbY75BqJJ0iTSNpslUB-RaniTTy6AjNEOQtTquC72Y0AvhUnNht5y2M4xa4HkSQjWZDjjQ6UdL2IUV9NAaT2pYrCRUIkPKO03lXI8U6i1H546qtWvGb7LDLc9FBZ5qfs3_rzIRp9Jrnv7RIg9CBpp_4xxkOWWUzRYcL0BmjgS34OatjH-xtEjf6VAn5zzP3Z8UwJvrlOXYPN3reO4JurRhtlQd89dArqc30xF1R4OMifWoFRL14Lws1R_foTTCh7g";
//        String pcitcToken = "xCxATVyxpZzV7JxlfXPe8QIyJXl9QjlfSOoXd3esqjrGzPobSGcAvQOszvyPOznFE6i2J3HQID8%3D";
//        ignoreCert();
//       ResponseEntity responseEntity = doGetRequest("http://cds.paas.sinopec.com/cds", new Header[]{new BasicHeader("Cookie",
//               "adfs_sso_token=" + adfsToken + ";" +
//                       "pcitc_sso_token=" + pcitcToken)});
//        System.out.println(responseEntity.getCode());
//        System.out.println(responseEntity.getResponseBody());

        System.out.println(new SimpleDateFormat("yyyy MM-dd HH:mm:ss SSS").format(new Date()) );
    }
}
