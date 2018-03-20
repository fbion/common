import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import work.shyk.LoginUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * description： <br>
 * createTime: 2018/3/2011:38 <br>
 *
 * @author zzh
 */
public class TestJackson {

    public static void main(String[] args) throws IOException {
        List<LoginUser> users = new ArrayList<>();
        LoginUser loginUser = new LoginUser();
        loginUser.setCurrentProjectId("0001");
        loginUser.setUserAccount("0001");
        loginUser.setUserId("0001");
        loginUser.setUserName("0001");
        loginUser.setUserType(0);
        loginUser.setRoleIds(new ArrayList<>());
        users.add(loginUser);
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        System.out.println(mapper.writeValueAsString(users));
        JavaType javaType2 = mapper.getTypeFactory().constructParametricType(List.class, LoginUser.class);
        List<LoginUser> temp = mapper.readValue(mapper.writeValueAsString(users), javaType2);
        System.out.println(temp);
    }
}
