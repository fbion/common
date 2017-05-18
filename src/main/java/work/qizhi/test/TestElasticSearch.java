package work.qizhi.test;

import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;
import java.net.InetAddress;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by Administrator on 2016/7/25.
 */
public class TestElasticSearch {
    public static void main(String[] args) throws IOException {
        TransportClient client = TransportClient.builder().build().addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("bbs.smartnlp.cn"), 9300));
//        client.
// on shutdown
        System.out.println("123");
        CountResponse response = client.prepareCount("test")
                .setQuery(termQuery("_type", "type1"))
                .execute()
                .actionGet();
        System.out.println(response.getCount());
        client.close();

//        String json = "{" +
//                "\"user\":\"kimchy\"," +
//                "\"postDate\":\"2013-01-30\"," +
//                "\"message\":\"trying out Elasticsearch\"" +
//                "}";
//
//        IndexResponse response1 = client.prepareIndex("test", "tweet", "1")
//                .setSource(jsonBuilder()
//                        .startObject()
//                        .field("user", "kimchy")
//                        .field("postDate", new Date())
//                        .field("message", "trying out Elasticsearch")
//                        .endObject()
//                )
//                .get();
    }
}
