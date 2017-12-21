package test;

/**
 * description: <br>
 * createTime: 2017/11/219:48 <br>
 *
 * @author zzh
 */
public class GroupInfo {

    /**
     * 组织名称
     */
    private String groupName;

    /**
     * 组织描述
     */
    private String description;

    /**
     * 组织是否活动的
     */
    private boolean isActive;


    public String getGroupName() {
        return groupName;
    }


    public String getDescription() {
        return description;
    }


    public boolean isActive() {
        return isActive;
    }


    public GroupInfo setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }


    public GroupInfo setDescription(String description) {
        this.description = description;
        return this;
    }


    public GroupInfo setActive(boolean active) {
        isActive = active;
        return this;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"groupName\":\"")
                .append(groupName).append('\"');
        sb.append(",\"description\":\"")
                .append(description).append('\"');
        sb.append(",\"isActive\":")
                .append(isActive);
        sb.append('}');
        return sb.toString();
    }
}
