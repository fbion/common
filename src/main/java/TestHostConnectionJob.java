import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * description: <br>
 * createTime: 2018/4/2711:20 <br>
 *
 * @author zzh
 */
public class TestHostConnectionJob {
    static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    static boolean isHostConnectable(String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Host[] hosts = Host.values();
        for (Host host : hosts) {
            new Thread(() -> {
                while (true) {
                    try {
                        System.err.print(sdf.format(new Date()) + "\t\t" + host.name() + " server:" + host.getHost());
                        if (!isHostConnectable(host.getHost(), 22)) {
                            System.err.println(" can't connect---------------");
                        } else {
                            System.err.println(" is OK");
                        }
                        TimeUnit.SECONDS.sleep(5);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
enum Host {
    DEV() {
        @Override
        public String getHost() {
            return "10.248.28.57";
        }
    }, UAT() {
        @Override
        public String getHost() {
            return "10.248.28.201";
        }
    }, PRO() {
        @Override
        public String getHost() {
            return "10.248.14.197";
        }
    };


    public abstract String getHost();
}