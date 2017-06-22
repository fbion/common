package utils.file.interfaces.impl;

import utils.file.interfaces.IOperator;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 描述： <br>
 * 创建时间: 2017/6/229:08 <br>
 *
 * @author 周志辉
 */
public class InsertString implements IOperator {
    @Override
    public void operator(File file) {
//        System.out.println("In operator : " + basePath + relativePath);
        if(!(file.getName()).contains(".htm")) {
            return;
        }
        try(RandomAccessFile rf = new RandomAccessFile(file, "rw");) {
            long length = rf.length();
            String start = "<!-- " + file.getAbsolutePath() + " :Start-->\r\n";
            String end = "\r\n<!-- " + file.getAbsolutePath() + " :End-->";
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
    }
}