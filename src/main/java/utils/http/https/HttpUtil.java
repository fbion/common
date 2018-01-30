package utils.http.https;

import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
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
import utils.http.HttpUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;

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
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

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


    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient getHttpClient() {
        //采用绕过验证的方式处理https请求
        SSLContext sslcontext = null;
        try {
            sslcontext = createIgnoreVerifySSL();
            // 设置协议http和https对应的处理socket链接工厂的对象
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslcontext))
                    .build();
            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            connManager = new PoolingHttpClientConnectionManager();
            connManager.setMaxTotal(50);// 整个连接池最大连接数
            connManager.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2

            //创建自定义的httpclient对象
            return HttpClients.custom().setConnectionManager(connManager).
                    setSSLHostnameVerifier((val1, val2) -> true).build();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            logger.error("HttpUtils初始化失败", e);
        }
        return null;
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
        HttpEntity entity = (contentType == null ? new StringEntity(postData) :
                new StringEntity(postData, contentType));
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


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException, ParseException {

//        ResponseEntity ResponseEntity1 = doGetRequest("https://baidu.com");
//        System.out.println(ResponseEntity1.getResponseBody());
//        System.out.println("-----------------------------------------------------------------------------");
//
//        ResponseEntity responseEntity2 = doGetRequest("https://uat-jira.paas.sinopec.com/login.jsp");
//        System.out.println(responseEntity2.getResponseBody());

//        String pcitcToken = "xCxATVyxpZzV7JxlfXPe8QIyJXl9QjlfSOoXd3esqjrGzPobSGcAvZRtG2pE04gshV%2BzVTKP6Dk%3D";
//       ResponseEntity responseEntity = doGetRequest("http://docker.cts.paas-dev.sinopec.com/cts/index", new Header[]{new BasicHeader("Cookie",
//                       "pcitc_sso_token=" + pcitcToken)});
//        System.out.println(responseEntity.getCode());
//        System.out.println(responseEntity.getResponseBody());
    }
}
