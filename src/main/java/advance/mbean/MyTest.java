package advance.mbean;

/**
 * description: 注册的MBean类必须满足以下条件：1、实现了接口，2、接口名称是实现类名称后加MBean，3、接口和实现类在同一个包下<br>
 * createTime: 2018/6/111:17 <br>
 *
 * @author zzh
 */
public class MyTest implements MyTestMBean {

    public void test() {
        System.out.println("Hello World!");
    }

}
