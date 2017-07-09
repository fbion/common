package utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：ZipUtils</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/4/18 17:24<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/4/18 17:24<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class ZipUtils {
    private static final int buffer = 2048;

    static final int BUFFER = 8192;

    /**
     * 解压Zip文件
     * @param path 文件目录
     */
    public static void unZip(String path, String desDirectory) {
        unZip(path, desDirectory, "UTF-8");
    }

    /**
     * 解压Zip文件
     * @param path 文件目录
     */
    public static void unZip(String path, String desDirectory, String charset) {
        int count = -1;
        File file = null;
        new File(desDirectory).mkdir(); //创建保存目录
        try(ZipFile zipFile = new ZipFile(path, Charset.forName(charset))) {
            Enumeration<?> entries = zipFile.entries();
            while(entries.hasMoreElements()) {
                byte buf[] = new byte[buffer];
                ZipEntry entry = (ZipEntry)entries.nextElement();
                String filename = entry.getName();
                boolean ismkdir = false;
                if(filename.lastIndexOf("/") != -1){ //检查此文件是否带有文件夹
                    ismkdir = true;
                }
                filename = desDirectory + "/" + filename;

                if(entry.isDirectory()){ //如果是文件夹先创建
                    file = new File(filename);
                    file.mkdirs();
                    continue;
                }
                file = new File(filename);
                if(!file.exists()){ //如果是目录先创建
                    if(ismkdir){
                        new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); //目录先创建
                    }
                }
                file.createNewFile(); //创建文件
                try(InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedOutputStream bos = new BufferedOutputStream(fos, buffer)) {
                    while((count = is.read(buf)) > -1) {
                        bos.write(buf, 0, count);
                    }
                    bos.flush();
                }
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * 解压Zip文件
     * @param inputStream 文件输入流
     */
    public static void unZip(InputStream inputStream, String desDirectory)
    {
        int count = -1;
        File file = null;
        new File(desDirectory).mkdir(); //创建保存目录
        try(ZipInputStream zipFile = new ZipInputStream(inputStream)) {
            ZipEntry entry;
            while((entry = zipFile.getNextEntry()) != null)
            {
                byte buf[] = new byte[buffer];
                String filename = entry.getName();
                boolean ismkdir = false;
                if(filename.lastIndexOf("/") != -1){ //检查此文件是否带有文件夹
                    ismkdir = true;
                }
                filename = desDirectory + "/" + filename;

                if(entry.isDirectory()) { //如果是文件夹先创建
                    file = new File(filename);
                    file.mkdirs();
                    continue;
                }
                file = new File(filename);
                if(!file.exists()) { //如果是目录先创建
                    if(ismkdir){
                        new File(filename.substring(0, filename.lastIndexOf("/"))).mkdirs(); //目录先创建
                    }
                }
                file.createNewFile(); //创建文件
                try(FileOutputStream fos = new FileOutputStream(file);
                    BufferedOutputStream bos = new BufferedOutputStream(fos, buffer)) {
                    while((count = zipFile.read(buf)) > -1) {
                        bos.write(buf, 0, count);
                    }
                    bos.flush();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    /**
     * 压缩为jar包，指定的目录不打入jar包，只把目录里的文件打进jar包
     * @param srcPathName 被压缩的文件/文件夹
     */
    public static void jar(String srcPathName, String zipFilePath) {
        File file = new File(srcPathName);
        if (!file.exists()){
            throw new RuntimeException(srcPathName + "不存在！");
        }
        if (!file.isDirectory()){
            throw new RuntimeException(srcPathName + "不是目录！");
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            String basedir = "";
            for (File file1 : file.listFiles()) {
                compressByType(file1, out, basedir);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 执行压缩操作
     * @param srcPathName 被压缩的文件/文件夹
     */
    public static void zip(String srcPathName, String zipFilePath) {
        File file = new File(srcPathName);
        if (!file.exists()){
            throw new RuntimeException(srcPathName + "不存在！");
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
            CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,new CRC32());
            ZipOutputStream out = new ZipOutputStream(cos);
            String basedir = "";
            compressByType(file, out, basedir);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断是目录还是文件，根据类型（文件/文件夹）执行不同的压缩方法
     * @param file
     * @param out
     * @param basedir
     */
    private static void compressByType(File file, ZipOutputStream out, String basedir) {
        /* 判断是目录还是文件 */
        if (file.isDirectory()) {
            compressDirectory(file, out, basedir);
        } else {
            compressFile(file, out, basedir);
        }
    }

    /**
     * 压缩一个目录
     * @param dir
     * @param out
     * @param basedir
     */
    private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
        if (!dir.exists()){
            return;
        }

        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            /* 递归 */
            compressByType(files[i], out, basedir + dir.getName() + "/");
        }
    }

    /**
     * 压缩一个文件
     * @param file
     * @param out
     * @param basedir
     */
    private static void compressFile(File file, ZipOutputStream out, String basedir) {
        if (!file.exists()) {
            return;
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            ZipEntry entry = new ZipEntry(basedir + file.getName());
            out.putNextEntry(entry);
            int count;
            byte data[] = new byte[BUFFER];
            while ((count = bis.read(data, 0, BUFFER)) != -1) {
                out.write(data, 0, count);
            }
            bis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
//        jar("D:\\test\\jar\\Info", "D:\\test\\jar\\Info.jar");

//        unZip("D:\\test\\projectjar\\smartregulation-datasource-0.0.1-SNAPSHOT.jar", "D:\\test\\projectjar\\smartregulation-datasource-0.0.1-SNAPSHOT");
//        jar("D:\\test\\projectjar\\smartregulation-datasource-0.0.1-SNAPSHOT", "D:\\test\\projectjar\\smartregulation-datasource-0.0.1-SNAPSHOT.jar");
    }
}
