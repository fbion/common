package thinking.chapter20.example003;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Member</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/10 8:45<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/10 8:45<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
@DBTable(name = "MEMBER")
public class Member {
    @SQLString(30) String firstName;
    @SQLString(50) String lastName;
    @SQLInteger
    Integer age;
    @SQLString(value = 30, constraints = @Constraints(primaryKey = true)) String handle;
    static int memberCount;


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public Integer getAge() {
        return age;
    }


    public String getHandle() {
        return handle;
    }


    @Override
    public String toString() {
        return handle;
    }
}
