package work.shyk;

/**
 * SSO的Token实体类
 *
 * @author 周志辉
 * @Description
 * @created 2017年11月7日 上午12:18:58
 * @history
 * @see
 */
public class SSOToken {

    private String userName;

    private String displayName;

    private long timestamp;


    public String getUserName() {
        return userName;
    }


    public long getTimestamp() {
        return timestamp;
    }


    public SSOToken setUserName(String userName) {
        this.userName = userName;
        return this;
    }


    public SSOToken setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }


    public String getDisplayName() {
        return displayName;
    }


    public SSOToken setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }
}
