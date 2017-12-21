package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * description: <br>
 * createTime: 2017/11/2016:45 <br>
 *
 * @author zzh
 */
public class Test39 {

    public static void main(String[] args) {
        JSONObject jsonObject = JSON.parseObject("{\"groupMember\":\"\"}");
        JSONObject object = JSON.parseObject(jsonObject.getString("groupMember"));
//        List<GroupInfo> groupInfos = JSON.parseObject(object.toJSONString(), new TypeReference<List<GroupInfo>>(){});
        System.out.println(object);
    }
}
