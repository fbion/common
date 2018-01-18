package utils.sftp.example1;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Vector;

/**
 * description： <br>
 * createTime: 2018/1/178:43 <br>
 *
 * @author zzh
 */
public class DownLoadThread implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SftpClient.class);

    private ChannelSftp channel = null;

    private Session session = null;

    private ProgressMonitorByBytes monitor = null;

    private String remotef = null;

    private String remotep = null;

    private String lf = null;

    private String ftpuser = null;

    private String ftphost = null;

    private String transid = null;


    public DownLoadThread(Session ss, String rf, String rp, String lf, String host, String user, String tid) {
        this.session = ss;
        this.remotef = rf;
        this.remotep = rp;
        this.lf = lf;
        this.ftphost = host;
        this.ftpuser = user;
        this.transid = tid;
    }


    @Override
    public void run() {

        try {
            channel = getOpenCh(session, ftphost, ftpuser);
            monitor = new ProgressMonitorByBytes(transid, remotef, this.getRemoteFilesize1(channel, remotef, remotep));
            downloadFile(this.remotef, this.remotep, this.lf);
            closeChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public ChannelSftp getOpenCh(Session ss, String ftphost, String ftpusername) {
        try {
            LOGGER.debug("Opening SFTP Channel.");
            channel = (ChannelSftp) ss.openChannel("sftp"); // 打开SFTP通道
            channel.connect(); // 建树SFTP通道的连接
            LOGGER.debug("Connected successfully to ftpHost = " + ftphost
                    + ",as ftpUserName = " + ftpusername + ", returning: "
                    + channel);
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return channel;
    }


    public void closeChannel() {
        try {
            if (channel != null) {
                channel.disconnect();
            }
        } catch (Exception e) {
            LOGGER.error("close sftp error", e);
        }
    }


    public void downloadFile(String remoteFile, String remotePath, String localFile) {
        File file = new File(localFile);
        int mode;
        try {
            if (!file.exists()) {
                file.createNewFile();
                mode = ChannelSftp.OVERWRITE;

            } else {
                mode = ChannelSftp.RESUME;
            }
            if (StringUtils.isNoneBlank(remotePath)) {
                channel.cd(remotePath);
            }
            channel.get(remoteFile, localFile
                    , monitor
                    , mode);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Download file error", e);
        }
    }


    public long getRemoteFilesize1(ChannelSftp cf, String remoteFile, String remotepath) {

        Object o = null;
        long s = 0;
        try {
            Vector v = cf.ls(cf.pwd() + "/" + remotepath + "/" + remoteFile);
            if (v != null && v.size() == 1) {
                o = v.firstElement();
            }
            //System.out.println();
            ChannelSftp.LsEntry cl = (ChannelSftp.LsEntry) o;
            s = cl.getAttrs().getSize();
            System.out.println(s + "(bytes)");
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return s;
    }


}
