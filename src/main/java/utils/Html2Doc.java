package utils;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * description： <br>
 * createTime: 2017/10/1111:20 <br>
 *
 * @author zzh
 */
public class Html2Doc {

    public boolean writeWordFile(String filepath) {
        boolean flag = false;
        ByteArrayInputStream bais = null;
        FileOutputStream fos = null;
        try {
            String content = readFile(filepath);
            byte b[] = content.getBytes();
            bais = new ByteArrayInputStream(b);
            POIFSFileSystem poifs = new POIFSFileSystem();
            DirectoryEntry directory = poifs.getRoot();
            DocumentEntry documentEntry = directory.createDocument(
                    "WordDocument", bais);
            fos = new FileOutputStream("temp.doc");
            poifs.writeFilesystem(fos);
            bais.close();
            fos.close();
        } catch (Exception e) {

        }
        return flag;

    }


    public String readFile(String filename) throws Exception {
        StringBuffer buffer = new StringBuffer("");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filename)),
                "GBK"))) {
            while (br.ready()) {
                buffer.append(br.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }


    public static void main(String[] args) {
        new Html2Doc().writeWordFile("d:\\1.html");
    }
}