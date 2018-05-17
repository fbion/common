package ebook.thinking.chapter18;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/22.
 */
public class BufferedInputFile
{
    public static String read(String fileName)
    {
        StringBuffer sb = new StringBuffer("");
        File file = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String temp;
            while((temp = br.readLine()) != null)
            {
                sb.append(temp + "\n");
            }
            return sb.toString();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
