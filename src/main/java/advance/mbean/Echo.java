package advance.mbean;

/**
 * description： <br>
 * createTime: 2018/2/2217:13 <br>
 *
 * @author zzh
 */
public class Echo  implements EchoMBean {

    @Override
    public void print(String yourName) {
        System.out.println("Hi " + yourName + "!");
    }
}
