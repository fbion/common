package test.base;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import play.libs.Json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */
public class StrangeInteger {
    public static void main(String[] args) throws IOException {
        Map<String, Integer> map = new HashMap();
        map.put("customChatCount", 1);
        map.put("robotChatCount", 2);
        JsonNode temp = Json.toJson(map);
        System.out.println(JSON.parse(temp.toString()));
        System.out.println(temp);
//        Integer i1 = 128;
//        Integer i2 = 127;
//        i2++;
//        int i = 128;
//        System.out.println(i1 == i);
//        System.out.println(i1 == i2);
    }
}
