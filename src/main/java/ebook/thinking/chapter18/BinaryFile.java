package ebook.thinking.chapter18;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/22.
 */
public class BinaryFile {
    public static byte[] read(File bFile)
    {
        try(BufferedInputStream bf = new BufferedInputStream(new FileInputStream(bFile)))
        {
            byte[] data = new byte[bf.available()];
            bf.read(data);
            return data;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] read(String fileName)
    {
        return read(new File(fileName));
    }
}
