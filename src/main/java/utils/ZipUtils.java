package utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

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

    /**
     * 解压Zip文件
     * @param path 文件目录
     */
    public static void unZip(String path, String desDirectory) {
        int count = -1;
        File file = null;
        new File(desDirectory).mkdir(); //创建保存目录
        try(ZipFile zipFile = new ZipFile(path, Charset.forName("gbk"))) {
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


    public static void main(String[] args) throws FileNotFoundException {
//        unZip(new FileInputStream("D:\\D\\after91.zip"), "D:\\D");

        unZip("D:\\D\\after91.zip", "D:\\D");
    }
}
