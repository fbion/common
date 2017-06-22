package utils.file;


import utils.file.interfaces.IOperator;
import utils.file.interfaces.impl.InsertInnerClass;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Administrator on 2016/6/8.
 * 递归遍历文件夹对文件进行操作工具类
 */
public class DirectoryRecurseOperatorUtil {
    /**
     * 方法描述: 递归遍历文件夹对文件进行处理，处理操作定义为IOperator接口类型<br>
     * 
     * @author 周志辉
     * @param   filePath    文件路径
     * @param   operator        操作类
     * @return
     * @throw 
     */
    public static void recurseDirectory(String filePath, IOperator operator) throws IOException {
        File file = new File(filePath);
        if(!file.exists()) {
            System.exit(1);
        }
        if(file.isDirectory()) {
            String[] fileNames = file.list();
            for (int i = 0; i < fileNames.length; i++) {
                recurseDirectory(filePath + "\\" + fileNames[i],  operator);
            }
        } else {
            operator.operator(file);
        }
    }

    public static boolean operator(String basePath, String relativePath) {
        if(!(basePath + relativePath).contains(".htm")) {
            return false;
        }
        try(RandomAccessFile rf = new RandomAccessFile(basePath + relativePath, "rw");) {
            long length = rf.length();
            String start = "<!-- " + relativePath + " :Start-->\r\n";
            String end = "\r\n<!-- " + relativePath + " :End-->";
            byte[] bs = start.getBytes();
            byte[] be = end.getBytes();
            long bslength = bs.length;
            long belength = be.length;
            long totalLength = length + bslength + belength;
            rf.setLength(totalLength);
            rf.seek(length + bslength);
            rf.write(be);
            for(long i = length + bslength - 1; i > bslength - 1; i--){
                rf.seek(i - bs.length);
                byte temp = rf.readByte();
                rf.seek(i);
                rf.writeByte(temp);
            }
            rf.seek(0);
            rf.write(bs);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    static int count = 0;
    public static void main(String[] args) throws IOException {
//        recurseDirectory("F:\\idea-projects\\qizhi-cloud\\app", "", (file) -> true, new FindString(), ".copy(");
        recurseDirectory("D:\\zzh\\codes\\common\\src\\main\\java\\tttt", new InsertInnerClass("Inner Valid Validator Entity".split(" ")));
    }
}