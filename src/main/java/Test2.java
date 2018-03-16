import org.apache.catalina.LifecycleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description： <br>
 * createTime: 2018/2/211:37 <br>
 *
 * @author zzh
 */
public class Test2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(Test2.class);

    public static void main(String[] args) throws LifecycleException {
        float nn = 6;
        double d = 6.5;
        System.out.println(nn == d);
        nn = 0.1f;
        d = 0.1;
        System.out.println(nn == d);
    }
}
