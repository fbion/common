package utils.sftp.example1;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

/**
 * description： <br>
 * createTime: 2018/1/1617:01 <br>
 *
 * @author zzh
 */
public class SftpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(SftpClient.class);

    private static Session session = null;


    public Session getConnect(Map<String, String> serverMap) {
        String ftpHost = serverMap.get(SFTPConstants.SFTP_SERVER_HOST);
        String port = serverMap.get(SFTPConstants.SFTP_SERVER_PORT);
        String ftpUserName = serverMap.get(SFTPConstants.SFTP_SERVER_USERNAME);
        String ftpPassword = serverMap.get(SFTPConstants.SFTP_SERVER_PASSWORD);

        // 默认的端口22 此处我是定义到常量类中；
        int ftpPort = SFTPConstants.SFTP_DEFAULT_PORT;

        // 判断端口号是否为空，如果不为空，则赋值
        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }
        JSch jsch = new JSch(); // 创建JSch对象
        // 按照用户名,主机ip,端口获取一个Session对象
        try {
            session = jsch.getSession(ftpUserName, ftpHost, ftpPort);

            LOGGER.debug("Session created.");
            if (ftpPassword != null) {
                session.setPassword(ftpPassword); // 设置密码
            }

            // 具体config中需要配置那些内容，请参照sshd服务器的配置文件/etc/ssh/sshd_config的配置
            Properties config = new Properties();

            // 设置不用检查hostKey
            // 如果设置成“yes”，ssh就会自动把计算机的密匙加入“$HOME/.ssh/known_hosts”文件，
            // 并且一旦计算机的密匙发生了变化，就拒绝连接。
            config.put("StrictHostKeyChecking", "no");

            // UseDNS指定，sshd的是否应该看远程主机名，检查解析主机名的远程IP地址映射到相同的IP地址。
            // 默认值是 “yes” 此处是由于我们SFTP服务器的DNS解析有问题，则把UseDNS设置为“no”
            config.put("UseDNS", "no");

            session.setConfig(config); // 为Session对象设置properties

            session.setTimeout(SFTPConstants.SFTP_DEFAULT_TIMEOUT); // 设置timeout时候
            session.connect(); // 经由过程Session建树链接
            LOGGER.debug("Session connected.");

        } catch (JSchException e) {
            e.printStackTrace();
        }
        return session;
    }


    public static void main(String[] args) throws Exception {
        Random rand = new Random();
        SftpClient sftpClient = new SftpClient();
        Map<String, String> ftpServerMap = new HashMap<String, String>();
        ftpServerMap.put((String) SFTPConstants.SFTP_SERVER_HOST, "10.248.28.57");
        ftpServerMap.put((String) SFTPConstants.SFTP_SERVER_USERNAME, "scapp");
        ftpServerMap.put((String) SFTPConstants.SFTP_SERVER_PASSWORD, "Pcitc2017!");
        ftpServerMap.put((String) SFTPConstants.SFTP_SERVER_PORT, "22");
        sftpClient.getConnect(ftpServerMap);

        String[] filenames = {"jdk-8u151-macosx-x64.dmg", "appium-desktop-1.2.3.dmg", "gradle-4.0.2.rar", "appium-1.5.3.dmg"};
        filenames = new String[]{"redis-4.0.6.tar.gz"};
        String localDirectory = "E:/test/";
        String remoteDirectory = "";

        List<Thread> lst = new ArrayList<Thread>();
        for (String filename : filenames) {
            DownLoadThread downLoadThread = new DownLoadThread(session, filename, remoteDirectory, localDirectory + filename,
                    ftpServerMap.get(SFTPConstants.SFTP_SERVER_HOST),
                    ftpServerMap.get(SFTPConstants.SFTP_SERVER_USERNAME),
                    "TRANS1" + rand.nextInt(100));
            Thread thread = new Thread(downLoadThread);
            thread.start();
            lst.add(thread);
        }

        Thread t3 = new Thread(() -> {
            while (true) {
                boolean nonlive = true;
                for (int i = 0; i < lst.size(); i++) {
                    if (lst.get(i).isAlive()) {
                        nonlive = false;
                        break;
                    }
                }
                if (nonlive) {
                    System.out.println("No Active transmission, exit!");
                    session.disconnect();
                    break;
                }
            }
        });
        t3.start();
    }
}
