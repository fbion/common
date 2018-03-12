import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description： <br>
 * createTime: 2017/12/717:49 <br>
 *
 * @author zzh
 */
public class Test {

    /**
     * 添加系统日志
     */
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    private static CloseableHttpClient httpClient;

    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(90000).setConnectionRequestTimeout(90000)
            .setSocketTimeout(90000).setCircularRedirectsAllowed(false).build();

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
        SSLContext sslcontext;
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

    public static CloseableHttpResponse doGetRequest(String url, Header[] headers) throws
            IOException {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        httpGet.setHeaders(headers);
        return httpClient.execute(httpGet);
    }


    public static CloseableHttpResponse doPostRequest(String url, byte[] body,
                                                      Header[]
                                                              headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(new ByteArrayEntity(body));
        httpPost.setHeaders(headers);
        return httpClient.execute(httpPost);
    }

    public static CloseableHttpResponse doPostRequest(String url, Map<String,String> parameterMap,
                                                      Header[] headers) throws IOException {
        //设定表单需要提交的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
            params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        httpPost.setHeaders(headers);
        return httpClient.execute(httpPost);
    }

    public static void main(String[] args) throws IOException {
        Map<String,String> parameterMap = new HashMap<>();
        parameterMap.put("userName", "zhihui.zhou");
        parameterMap.put("password", "shyk789");
        parameterMap.put("backUrl", "http://cts.paas-dev.sinopec.com/cts/index.jsp");
        CloseableHttpResponse response = doPostRequest("https://uic.aliyun.sinopec" +
                ".com/sso/login?back_url=http%3A%2F%2Fcts.paas-dev.sinopec.com%2Fcts%2Findex.jsp",
                parameterMap, null);
        System.out.println(response.getStatusLine().getStatusCode());
        for (Header header : response.getAllHeaders()) {
            if("Set-Cookie".equals(header.getName()) && header.getValue().startsWith("pcitc_sso_token")) {
                System.out.println(header.getValue().split(";")[0].split("=")[1]);
            }
        }
//        String content = EntityUtils.toString(response.getEntity());
//        System.out.println(content);
    }
}
