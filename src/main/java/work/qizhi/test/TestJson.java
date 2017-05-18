package work.qizhi.test;

import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/18.
 */
public class TestJson {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        JsonNode node = Json.parse(list.toString());
        System.out.println(node);
    }
}
