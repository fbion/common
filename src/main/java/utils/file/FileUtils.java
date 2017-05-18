package utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：FileUtils</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/4/26 17:06<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/4/26 17:06<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class FileUtils {
    /**
     * <p>方法描述: 在本地创建一个文件夹</p>
     * <p>方法备注: 可用于将Job的执行结果等文件，放到目标位置</p>
     * @param path 要创建的目录
     * @return 返回创建的结果 true-成功 false-失败
     * <p>创建人：王东辉</p>
     * <p>创建时间：2016-11-11 下午5:02:21</p>
     */
    public static boolean createDir(String path){
        path = path.replaceAll("[\\\\/]*$", "");
        File file = new File(path);
        if(!file.exists()) {
            if(path.length() > 0 && path.indexOf("/") >= 0) {
                if(!createDir(path.substring(0, path.lastIndexOf("/")))) {
                    return false;
                }
            }
            if(!file.mkdir()) {
                return false;
            }
        }
        return true;
    }


    /**
     * <p>方法描述: 按指定路径删除文件或目录</p>
     * <p>方法备注: </p>
     * @param path 要删除的文件或目录的全路径
     * @return 返回创建的结果 true-成功 false-失败
     * <p>创建人：周志辉</p>
     * <p>创建时间：2016-11-11 下午5:02:21</p>
     */
    public static boolean deleteFiles(String path){
        path = path.replaceAll("[\\\\/]*$", "");
        File file = new File(path);
        if(file.exists()) {
            if(file.isDirectory()) {
                String[] fileNames = file.list();
                for (String fileName : fileNames) {
                    deleteFiles(path + "/" + fileName);
                }
            }
            file.delete();
        }
        return true;
    }


    /**
     * <p>方法描述: 按指定路径删除文件或目录</p>
     * <p>方法备注: </p>
     * @param srcPath 要复制的文件或目录的全路径
     * @param desPath 要复制到的文件或目录的全路径
     * @return 返回创建的结果 true-成功 false-失败
     * <p>创建人：周志辉/p>
     * <p>创建时间：2016-11-11 下午5:02:21</p>
     */
    public static boolean copyFiles(String srcPath, String desPath){
        srcPath = srcPath.replaceAll("[\\\\/]*$", "");
        desPath = desPath.replaceAll("[\\\\/]*$", "");
        File srcfile = new File(srcPath);
        File desfile = new File(desPath);
        if(srcfile.exists()) {
            if(srcfile.isDirectory()) {
                desfile.mkdir();
                String[] fileNames = srcfile.list();
                for (String fileName : fileNames) {
                    copyFiles(srcPath + "/" + fileName, desPath + "/" + fileName);
                }
            } else {
                copyFile(srcPath, desPath);
            }
        }
        return true;
    }


    /**
     * <p>方法描述: 按指定路径删除文件或目录</p>
     * <p>方法备注: </p>
     * @param srcPath 要复制的文件或目录的全路径
     * @param desPath 要复制到的文件或目录的全路径
     * @return 返回创建的结果 true-成功 false-失败
     * <p>创建人：周志辉/p>
     * <p>创建时间：2016-11-11 下午5:02:21</p>
     */
    public static boolean copyFile(String srcPath, String desPath){
        try (BufferedReader br = new BufferedReader(new FileReader(srcPath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(desPath))) {
            String line = null;
            while((line = br.readLine()) != null){
                bw.write(line);
                bw.flush();
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(createDir("1\\2\\3"));
    }
}
