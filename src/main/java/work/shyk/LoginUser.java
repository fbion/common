package work.shyk;

import java.util.List;

/**
 * description： <br>
 * createTime: 2018/1/515:21 <br>
 *
 * @author zzh
 */
public class LoginUser {

    private String userId;

    private String userAccount;

    private String currentProjectId;

    private String userName;

    private Integer userType;

    private List<String> roleIds;


    public LoginUser setUserId(String userId) {
        this.userId = userId;
        return this;
    }


    public LoginUser setUserAccount(String userAccount) {
        this.userAccount = userAccount;
        return this;
    }


    public LoginUser setCurrentProjectId(String currentProjectId) {
        this.currentProjectId = currentProjectId;
        return this;
    }


    public LoginUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }


    public LoginUser setUserType(Integer userType) {
        this.userType = userType;
        return this;
    }


    public LoginUser setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
        return this;
    }


    public String getUserId() {
        return userId;
    }


    public String getUserAccount() {
        return userAccount;
    }


    public String getCurrentProjectId() {
        return currentProjectId;
    }


    public String getUserName() {
        return userName;
    }


    public Integer getUserType() {
        return userType;
    }


    public List<String> getRoleIds() {
        return roleIds;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":\"")
                .append(userId).append('\"');
        sb.append(",\"userAccount\":\"")
                .append(userAccount).append('\"');
        sb.append(",\"currentProjectId\":\"")
                .append(currentProjectId).append('\"');
        sb.append(",\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"roleIds\":")
                .append(roleIds);
        sb.append('}');
        return sb.toString();
    }
}
