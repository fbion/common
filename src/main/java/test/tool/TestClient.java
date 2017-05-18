package test.tool;

import com.ning.http.client.AsyncHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import test.utils.TestUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/3/19.
 */
@SuppressWarnings("deprecation")
public class TestClient
{
    /**
     * AsyncHttpClient
     */
    public static AsyncHttpClient client = new AsyncHttpClient();

    /**
     * HttpClient
     */
    public static HttpClient httpClient = new DefaultHttpClient();

    /**
     * 异步方式用get请求方式获取回复字符串
     *
     * @param url
     *            请求地址
     * @return 应答字符串
     */
    public static String getAsyncResponseByGetMethod(String url)
    {
        String response = null;
        try
        {
            // 获取回复体并以UTF-8解析
            byte[] bytes = client.prepareGet(url).setHeader("charset", "UTF-8")
                    .execute().get().getResponseBodyAsBytes();
            response = new String(bytes, 0, bytes.length, "UTF-8");
        }
        catch(ExecutionException | InterruptedException | IOException e)
        {
            e.printStackTrace();
        }
        return response;
    }

    public static String geResponseByGetMethod(String url)
            throws URISyntaxException, IOException
    {
        HttpGet request = new HttpGet();
        request.setURI(new URI(url));
        HttpResponse response = httpClient.execute(request);
        StringBuffer sb;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                response.getEntity().getContent(), "UTF-8")))
        {
            sb = new StringBuffer("");
            String line;
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null)
            {
                sb.append(line + NL);
            }
        }finally{
//            request.re();
            httpClient.getConnectionManager().closeIdleConnections(0, TimeUnit.MILLISECONDS);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        String response;
        for (int i = 0; i < 3; i++)
        {
            // response = getAsyncResponseByGetMethod("http://www.baidu.com");
            // client.close();
            response = geResponseByGetMethod("http://www.baidu.com");
            TestUtils.recordInFile(response, "f:\\test\\" + i + ".txt");
        }
    }
}
