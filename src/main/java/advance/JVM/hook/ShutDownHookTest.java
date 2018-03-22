package advance.JVM.hook;

import java.util.concurrent.TimeUnit;

/**
 * description： <br>
 * createTime: 2018/3/2214:07 <br>
 * 注册JVM关闭钩子在下面几种情况下执行
 * 1，JVM正常关闭
 * 2，System.exit()
 * 3，Ctrl+C中断关闭
 * 4，系统关闭
 * 5，OOM宕机
 * 6，kill pid命令（kill - 9 时不执行）
 * -Xmx 20m
 * @author zzh
 */
public class ShutDownHookTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("start");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("in shutdown hook");
        }));
        TimeUnit.SECONDS.sleep(5);
        byte[] b = new byte[500*1024*1024];
        System.out.println("end");
        System.exit(1);
    }
}
