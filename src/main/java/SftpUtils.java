

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

/**
 * description： <br>
 * createTime: 2018/1/1717:15 <br>
 *
 * @author zzh
 */
public class SftpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(SftpUtils.class);

    private static String host = "10.248.28.57";

    private static String port = "22";

    private static String username = "scapp";

    private static String password = "Pcitc2017!";


    public static Session getSession() throws JSchException {
        return getSession(500);
    }


    /**
     * Method Name：获取session对象
     * Method Description: <br>
     *
     * @param timeout
     * @return session对象
     * <p>creator：zzh
     * <p>createTime：2018/1/18 18:03
     */
    public static Session getSession(int timeout) throws JSchException {
        int ftpPort = 22;
        if (port != null && !port.equals("")) {
            ftpPort = Integer.valueOf(port);
        }
        JSch jsch = new JSch(); // 创建JSch对象
        Session session = jsch.getSession(username, host, ftpPort); // 根据用户名，主机ip，端口获取一个Session对象
        LOGGER.debug("Session created.");
        if (password != null) {
            session.setPassword(password); // 设置密码
        }
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config); // 为Session对象设置properties
        session.setTimeout(timeout); // 设置timeout时间
        return session;

    }


    public static ChannelSftp openChannel(Session session) throws JSchException {
        session.connect(); // 通过Session建立链接
        LOGGER.debug("Session connected.");

        LOGGER.debug("Opening Channel.");
        ChannelSftp channel = (ChannelSftp) session.openChannel("sftp"); // 打开SFTP通道
        channel.connect(); // 建立SFTP通道的连接
        LOGGER.debug("Connected successfully to host = " + host + ",as username = " + username
                + ", returning: " + channel);
        return channel;
    }


    /**
     * Method Name：上传文件或文件夹到指定路径下
     * Method Description: <br>
     *
     * @param localFiles     本地文件或文件夹路径
     * @param remoteFoldPath 远程路径
     * @return 操作结果
     * @throw <p>creator：zzh
     * <p>createTime：2018/1/18 17:57
     */
    public static boolean uploadFiles(String remoteFoldPath, String... localFiles) {
        Set<File> files = Sets.newHashSet();
        for (int i = 0; i < localFiles.length; i++) {
            if(localFiles[i]!= null && !StringUtils.isBlank(dealWithPath(localFiles[i]))) {
                files.add(new AbsoluteFile(localFiles[i]));
            }
        }
        if(files.size() == 0) {
            LOGGER.warn("没有文件可上传");
            return false;
        }
        return uploadFiles(remoteFoldPath, files);
    }


    /**
     * Method Name：上传文件或文件夹到指定路径下
     * Method Description: <br>
     *
     * @param localFiles     本地文件或文件夹路径
     * @param remoteFoldPath 远程路径
     * @return 操作结果
     * @throw <p>creator：zzh
     * <p>createTime：2018/1/18 17:57
     */
    public static boolean uploadFiles(String remoteFoldPath, Collection<File> localFiles) {
        Session session = null;
        ChannelSftp channel = null;
        try {
            session = getSession();
            channel = openChannel(session);
            uploadFiles(channel, remoteFoldPath, localFiles);
            return true;
        } catch (JSchException e) {
            LOGGER.error("sftp异常", e);
            e.printStackTrace();
        } catch (SftpException e) {
            LOGGER.error("sftp异常", e);
            e.printStackTrace();
        } finally {
            closeChannel(session, channel);
        }
        return false;
    }


    private static boolean uploadFiles(ChannelSftp channel, String remoteFoldPath,
                                       Collection<File> localFiles) throws SftpException {
        if (!mkdirAndCd(channel, remoteFoldPath)) {
            System.out.println("创建目录失败");
            return false;
        }
        String currentDirectory = channel.pwd();
        for (File localFile : localFiles) {
            if (!localFile.exists()) {
                LOGGER.error("要上传的文件不存在");
                return false;
            }
            if (localFile.isDirectory()) {
                uploadFiles(channel, localFile.getName(), Lists.newArrayList(localFile.listFiles()));
                channel.cd(currentDirectory);
            } else {
                try (InputStream input = new FileInputStream(localFile);
                     OutputStream os = channel.put(localFile.getName(), ChannelSftp.OVERWRITE)) {
                    long t1 = System.currentTimeMillis();
                    upfile(input, os);
//                    channel.put(localFile.getAbsolutePath(), localFile.getName(), ChannelSftp.OVERWRITE);
                    System.out.println("Time elapsed: " + (System.currentTimeMillis() - t1) + "(ms)");

                } catch (Exception e) {
                    LOGGER.error("Upload file error", e);
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Method Name：上传单个文件，可以重命名文件
     * Method Description: <br>
     *
     * @param localFile      本地文件路径
     * @param remoteFoldPath 远程目录路径
     * @param remoteFileName 远程文件名
     * @return 操作结果
     * @throw <p>creator：zzh
     * <p>createTime：2018/1/19 11:33
     */
    public static boolean uploadFile(String localFile, String remoteFoldPath, String remoteFileName) {
        Session session = null;
        ChannelSftp channel = null;
        try {
            session = getSession();
            channel = openChannel(session);
            if (!mkdirAndCd(channel, remoteFoldPath)) {
                System.out.println("创建目录失败");
                return false;
            }
            File lf = new File(localFile);
            if (!lf.exists()) {
                LOGGER.error("要上传的文件不存在");
                return false;
            }

            if (lf.isDirectory()) {
                LOGGER.error("该方法不支持上传文件夹");
                return false;
            }
            try (InputStream input = new FileInputStream(lf);
                 OutputStream os = channel.put(remoteFileName, ChannelSftp.OVERWRITE)) {
                // 改变当前路径到指定路径
                long t1 = System.currentTimeMillis();
                upfile(input, os);
                channel.put(localFile, remoteFileName, ChannelSftp.OVERWRITE);
                System.out.println("Time elapsed: " + (System.currentTimeMillis() - t1) + "(ms)");
                return true;
            } catch (Exception e) {
                LOGGER.error("Upload file error", e);
            }
        } catch (JSchException e) {
            LOGGER.error("sftp异常", e);
            e.printStackTrace();
        } finally {
            closeChannel(session, channel);
        }
        return false;
    }


    /**
     * Method Name：切换到指定路径下，如果不存在会逐级建目录
     * Method Description: <br>
     *
     * @param channelSftp channel对象
     * @param remotePath  远程路径
     * @return 操作结果
     * <p>creator：zzh
     * <p>createTime：2018/1/18 18:02
     */
    private static boolean mkdirAndCd(ChannelSftp channelSftp, String remotePath) {
        String temp = dealWithPath(remotePath);
        if ("".equals(temp)) {
            return true;
        }
        String[] dirs = temp.split("[\\\\/]+");
        try {
            String currentPath = channelSftp.pwd();
            for (String dir : dirs) {
                boolean flag = false;
                for (Object obj : channelSftp.ls(currentPath)) {
                    ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) obj;
                    if (dir.equals(entry.getFilename())) {
                        if (!entry.getAttrs().isDir()) {
                            LOGGER.error("创建文件夹失败，已存在与要建立的文件夹同名的文件！");
                            return false;
                        }
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    channelSftp.mkdir(dir);
                }
                channelSftp.cd(dir);
                currentPath += "/" + dir;
            }
            return true;
        } catch (SftpException e) {
            LOGGER.error("创建文件夹失败，发生异常", e);
            e.printStackTrace();
        }
        return false;
    }


    private static boolean upfile(InputStream is, OutputStream os) throws IOException {
        boolean res = false;
        byte[] b = new byte[1024 * 1024 * 100];
        int read;
        if (os != null) {
            do {
                read = is.read(b, 0, b.length);
                if (read > 0) {
                    os.write(b, 0, read);
                }
                os.flush();
            } while (read >= 0);
        }
        return res;
    }


    /**
     * Method Name：上传文件到指定路径
     * Method Description: <br>
     *
     * @param inputStream    输入流
     * @param remoteFoldPath 保存路径
     * @param remoteFileName 保存文件名
     * @return 操作结果
     * <p>creator：zzh
     * <p>createTime：2018/1/23 14:10
     */
    public static boolean uploadFile(InputStream inputStream, String remoteFoldPath, String remoteFileName) {
        Session session = null;
        ChannelSftp channel = null;
        try {
            session = getSession();
            channel = openChannel(session);
            if (!mkdirAndCd(channel, remoteFoldPath)) {
                System.out.println("创建目录失败");
                return false;
            }
            try (OutputStream os = channel.put(remoteFileName, ChannelSftp.OVERWRITE)) {
                // 改变当前路径到指定路径
                long t1 = System.currentTimeMillis();
                upfile(inputStream, os);
                System.out.println("Time elapsed: " + (System.currentTimeMillis() - t1) + "(ms)");
                return true;
            } catch (Exception e) {
                LOGGER.error("Upload file error", e);
            }
        } catch (JSchException e) {
            LOGGER.error("sftp异常", e);
            e.printStackTrace();
        } finally {
            closeChannel(session, channel);
        }
        return false;
    }


    /**
     * Method Name：上传文件到指定路径
     * Method Description: <br>
     *
     * @param bytes          读取到的文件字节内容
     * @param remoteFoldPath 保存路径
     * @param remoteFileName 保存文件名
     * @return 操作结果
     * @throw <p>creator：zzh
     * <p>createTime：2018/1/23 14:08
     */
    public static boolean uploadFile(byte[] bytes, String remoteFoldPath, String remoteFileName) {
        Session session = null;
        ChannelSftp channel = null;
        try {
            session = getSession();
            channel = openChannel(session);
            if (!mkdirAndCd(channel, remoteFoldPath)) {
                System.out.println("创建目录失败");
                return false;
            }
            try (OutputStream os = channel.put(remoteFileName, ChannelSftp.OVERWRITE)) {
                // 改变当前路径到指定路径
                long t1 = System.currentTimeMillis();
                if (!upfile(bytes, os)) {
                    return false;
                }
                System.out.println("Time elapsed: " + (System.currentTimeMillis() - t1) + "(ms)");
                return true;
            } catch (Exception e) {
                LOGGER.error("Upload file error", e);
            }
        } catch (JSchException e) {
            LOGGER.error("sftp异常", e);
            e.printStackTrace();
        } finally {
            closeChannel(session, channel);
        }
        return false;
    }


    private static boolean upfile(byte[] bytes, OutputStream os) {
        try {
            os.write(bytes);
            os.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean downloadFiles(String localPath, String... remotePath) {
        Set<String> files = Sets.newHashSet();
        for (String s : remotePath) {
             if(s != null) {
                 String temp = dealWithPath(s);
                 if(!StringUtils.isBlank(temp)) {
                     files.add(temp);
                 }
             }
        }
        if(files.size() == 0) {
            LOGGER.warn("没有文件可下载");
             return false;
        }
        return downloadFiles(localPath, files);
    }

    private static boolean downloadFiles(String localPath, Set<String> files) {
        Session session = null;
        ChannelSftp channel = null;
        try {
            session = getSession();
            channel = openChannel(session);
            Vector entrys = channel.ls(channel.pwd());
            String currentDirectory = channel.pwd();
            for (String file : files) {
                if (!downLoadFile(localPath, channel, file)) {
                    return false;
                }
                channel.cd(currentDirectory);
            }
            return true;
        } catch (JSchException e) {
            LOGGER.error("sftp异常", e);
            e.printStackTrace();
        } catch (SftpException e) {
            LOGGER.error("sftp异常", e);
            e.printStackTrace();
        } finally {
            closeChannel(session, channel);
        }
        return false;
    }

    private static boolean downLoadFile(String localPath, ChannelSftp channelSftp, String filePath) {
        int index = filePath.lastIndexOf("/");
        try {
            if (index > 0) {
                String path = filePath.substring(0, index);
                channelSftp.cd(filePath.substring(0, index));
            }
            String fileName = filePath.substring(index + 1);
            for (Object obj : channelSftp.ls(channelSftp.pwd())) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) obj;
                if (entry.getFilename().equals(fileName)) {
                    return downLoadEntry(localPath, channelSftp, entry);
                }
            }
            LOGGER.warn("要删除的文件或文件夹不存在");
        } catch (SftpException e) {
            LOGGER.warn("删除文件或文件夹发生异常", e);
            e.printStackTrace();
        }
        return false;
    }

    private static boolean downLoadEntry(String localPath, ChannelSftp channelSftp, ChannelSftp.LsEntry entry) {

        try {
            String currentFileName = entry.getFilename();
            if(entry.getAttrs().isDir()) {
                localPath += "/" + currentFileName;
                for (Object obj : channelSftp.ls(currentFileName)) {
                    ChannelSftp.LsEntry lsEntry = (ChannelSftp.LsEntry) obj;
                    if (!".".equals(lsEntry.getFilename()) && !"..".equals(lsEntry.getFilename())) {
                        channelSftp.cd(currentFileName);
                        createDir(localPath);
                        downLoadEntry(localPath, channelSftp, lsEntry);
                        channelSftp.cd("..");
                    }
                }
            } else {
                channelSftp.get(currentFileName, localPath + "/" + currentFileName);
            }
            return true;
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     *
     * Method Name：下载指定文件到本地
     * Method Description: <br>
     *
     * @param   remoteFile  待下载的文件名
     * @param   remotePath  待下载文件路径
     * @param   localFile   文件下载到本地的全路径
     * @return  操作结果
     * <p>creator：zzh
     * <p>createTime：2018/1/23 15:26
     */
    public static boolean downloadFile(String remoteFile, String remotePath, String localFile) {
        if(StringUtils.isBlank(remoteFile)) {
            LOGGER.warn("文件名不能为空");
            return false;
        }
        Session session = null;
        ChannelSftp channel = null;
        try {
            session = getSession();
            channel = openChannel(session);
            File file = new File(localFile);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                if (StringUtils.isNoneBlank(remotePath)) {
                    channel.cd(remotePath);
                }
                channel.get(remoteFile, localFile);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("Download file error", e);
            }
        } catch (JSchException e) {
            LOGGER.error("sftp异常", e);
            e.printStackTrace();
        } finally {
            closeChannel(session, channel);
        }
        return false;
    }


    /**
     *
     * Method Name：下载指定文件，返回文件字节
     * Method Description: <br>
     *
     * @param   remoteFile  待下载的文件名
     * @param   remotePath  待下载文件路径
     * @return  下载到的文件字节内容
     * <p>creator：zzh
     * <p>createTime：2018/1/23 15:28
     */
    public static byte[] downloadFileToBytes(String remoteFile, String remotePath) {
        if(StringUtils.isBlank(remoteFile)) {
            LOGGER.warn("文件名不能为空");
            return null;
        }
        Session session = null;
        ChannelSftp channel = null;
        try {
            session = getSession();
            channel = openChannel(session);
            if (StringUtils.isNoneBlank(remotePath)) {
                channel.cd(remotePath);
            }
            try (InputStream inputStream = channel.get(remoteFile);){
                Vector entrys = channel.ls(channel.pwd());
                int length = 0;
                for (Object obj : entrys) {
                    ChannelSftp.LsEntry  entry = (ChannelSftp.LsEntry) obj;
                    if(entry.getFilename().equals(remoteFile) && !entry.getAttrs().isDir()) {
                        long temp = entry.getAttrs().getSize();
                        if(temp > Integer.MAX_VALUE) {
                            LOGGER.warn("文件太大");
                            return null;
                        }
                        length = (int) temp;
                    }
                }
                byte[]  resullt = new byte[length + 1];
                inputStream.read(resullt);
                return resullt;
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("Download file error", e);
            }
        } catch (JSchException e) {
            LOGGER.error("sftp异常", e);
            e.printStackTrace();
        } catch (SftpException e) {
            LOGGER.error("sftp异常", e);
            e.printStackTrace();
        } finally {
            closeChannel(session, channel);
        }
        return null;
    }


    /**
     * Method Name：删除指定文件或文件夹
     * Method Description: <br>
     *
     * @param filePaths 待删除文件或文件夹路径
     * @return 操作结果
     * @throw <p>creator：zzh
     * <p>createTime：2018/1/19 16:03
     */
    public static boolean rm(String... filePaths) {
        if (filePaths == null) {
            LOGGER.warn("参数不能为null");
            return true;
        }
        Set<String> files = Sets.newHashSet();
        for (String filepath : filePaths) {
            if (filepath != null) {
                String temp = dealWithPath(filepath);
                if (!StringUtils.isBlank(temp)) {
                    files.add(temp);
                }
            }
        }
        if (files.size() == 0) {
            LOGGER.warn("没有要删除的文件");
            return true;
        }
        return rm(files);
    }


    /**
     * Method Name：删除指定文件或文件夹
     * Method Description: <br>
     *
     * @param files 待删除文件或文件夹路径
     * @return 操作结果
     * @throw <p>creator：zzh
     * <p>createTime：2018/1/19 16:03
     */
    public static boolean rm(Set<String> files) {
        Session session = null;
        ChannelSftp channel = null;
        try {
            session = getSession();
            channel = openChannel(session);

            String currentDirectory = channel.pwd();
            for (String file : files) {
                if (!rm(channel, file)) {
                    return false;
                }
                channel.cd(currentDirectory);
            }
            return true;
        } catch (JSchException e) {
            LOGGER.error("sftp连接异常", e);
            e.printStackTrace();
        } catch (SftpException e) {
            LOGGER.error("删除文件失败", e);
            e.printStackTrace();
        } finally {
            closeChannel(session, channel);
        }
        return false;
    }


    /**
     * Method Name：删除指定文件或文件夹
     * Method Description: <br>
     *
     * @param channelSftp ChannelSftp对象
     * @param filePath    待删除文件或文件夹路径
     * @return 操作结果
     * @throw <p>creator：zzh
     * <p>createTime：2018/1/19 16:03
     */
    private static boolean rm(ChannelSftp channelSftp, String filePath) {
        int index = filePath.lastIndexOf("/");
        try {
            if (index > 0) {
                channelSftp.cd(filePath.substring(0, index));
            }
            String fileName = filePath.substring(index + 1);
            for (Object obj : channelSftp.ls(channelSftp.pwd())) {
                ChannelSftp.LsEntry entry = (ChannelSftp.LsEntry) obj;
                if (entry.getFilename().equals(fileName)) {
                    return rmEntry(channelSftp, entry);
                }
            }
            LOGGER.warn("要删除的文件或文件夹不存在");
            return true;
        } catch (SftpException e) {
            LOGGER.warn("删除文件或文件夹发生异常", e);
            e.printStackTrace();
        }
        return false;
    }


    /**
     * Method Name：递归删除指定文件或文件夹
     * Method Description: <br>
     *
     * @param channelSftp ChannelSftp对象
     * @param entry       文件或文件夹对象
     * @return 操作结果
     * @throw <p>creator：zzh
     * <p>createTime：2018/1/19 16:03
     */
    private static boolean rmEntry(ChannelSftp channelSftp, ChannelSftp.LsEntry entry) {
        try {
            String currentFileName = entry.getFilename();
            if (!entry.getAttrs().isDir()) {
                channelSftp.rm(currentFileName);
            } else {
                for (Object obj : channelSftp.ls(currentFileName)) {
                    ChannelSftp.LsEntry lsEntry = (ChannelSftp.LsEntry) obj;
                    if (!".".equals(lsEntry.getFilename()) && !"..".equals(lsEntry.getFilename())) {
                        channelSftp.cd(currentFileName);
                        rmEntry(channelSftp, lsEntry);
                        channelSftp.cd("..");
                    }
                }
                channelSftp.rmdir(currentFileName);
            }
            return true;
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String dealWithPath(String path) {
        return path.replaceAll("^[\\\\/]+", "").
                replaceAll("[\\\\/]+$", "").
                replaceAll("[\\\\/]+", "/");
    }


    /**
     * <p>方法描述: 在本地创建一个文件夹</p>
     * <p>方法备注: 可用于将Job的执行结果等文件，放到目标位置</p>
     * <p>注意事项：本方法套件执行过程中会使用，非特殊情况，不要更改</p>
     * @param path 要创建的目录
     * @return 返回创建的结果 true-成功 false-失败
     * <p>创建人：周志辉</p>
     * <p>创建时间：2016-11-11 下午5:02:21</p>
     */
    private static boolean createDir(String path) {
        path = path.replaceAll("[\\\\/]*$", "");
        File file = new File(path);
        if (!file.exists()) {
            if (path.length() > 0 && path.indexOf("/") >= 0) {
                if (!createDir(path.substring(0, path.lastIndexOf("/")))) {
                    return false;
                }
            }
            if (!file.mkdir()) {
                return false;
            }
        }
        return true;
    }



    public static void closeChannel(Session session, ChannelSftp channel) {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }


//    public static void test() {
//        Session session = null;
//        ChannelSftp channel = null;
//        try {
//            session = getSession();
//            channel = openChannel(session);
//            System.out.println(channel.ls(channel.pwd()).size());
//        } catch (JSchException e) {
//            LOGGER.error("sftp连接异常", e);
//            e.printStackTrace();
//        } catch (SftpException e) {
//            LOGGER.error("删除文件失败", e);
//            e.printStackTrace();
//        } finally {
//            closeChannel(session, channel);
//        }
//    }


    static class AbsoluteFile extends File {

        public AbsoluteFile(String pathname) {
            super(pathname);
        }


        @Override
        public boolean equals(Object obj) {
            if (obj instanceof AbsoluteFile) {
                AbsoluteFile file = (AbsoluteFile) obj;
                return getAbsolutePath().equals(file.getAbsolutePath());
            }
            return false;
        }


        @Override
        public int compareTo(File pathname) {
            return getAbsolutePath().compareTo(pathname.getAbsolutePath());
        }


        @Override
        public int hashCode() {
            return getAbsolutePath().hashCode();
        }
    }


    public static void main(String[] args) throws JSchException {

        if(args.length >= 3) {
            System.out.println(downloadFiles(args[0], args[1], args[2]));
        } else {
            System.out.println(uploadFile("/home/scapp/cts2.0/cloud.pem", "/home/scapp","cloud.pem"));
        }
    }
}
