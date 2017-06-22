package utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Created by Administrator on 2015/12/11.
 */
public class FileOperator
{
    public static void operateFile(String fileName, IStringOperator operator)
            throws IOException
    {
        File srcFile = new File(fileName);
        if (!srcFile.exists())
        {
            System.out.println("File not exists.");
            System.exit(0);
        }

        File desFile = createRandomFile(fileName);
        if (!desFile.createNewFile())
        {
            System.out.println("Can't create temp File");
            System.exit(0);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "UTF-8"));
                BufferedWriter bw = new BufferedWriter(new FileWriter(desFile)))
        {
            String str = br.readLine();
            while (str != null)
            {
                str = operator.stringOperator(str);
                bw.write(str);
                bw.flush();
                str = br.readLine();
            }
        }
        srcFile.delete();
        desFile.renameTo(srcFile);
    }

    public static void operateFile(String baseName, String relativeName, IStringOperator2 operator)
            throws IOException
    {
        File srcFile = new File(baseName + relativeName);
        if (!srcFile.exists())
        {
            System.out.println("File not exists.");
            System.exit(0);
        }

        File desFile = createRandomFile(baseName + relativeName);
        if (!desFile.createNewFile())
        {
            System.out.println("Can't create temp File of : " + baseName + relativeName);
            return;
//            System.exit(0);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "UTF-8"));
             BufferedWriter bw = new BufferedWriter(new FileWriter(desFile)))
        {
            String str = br.readLine();
            while (str != null)
            {
                str = operator.stringOperator(relativeName, str);
                bw.write(str);
                bw.flush();
                str = br.readLine();
            }
        }
        srcFile.delete();
        desFile.renameTo(srcFile);
    }

    private static File createRandomFile(String srcFile)
    {
        Random rand = new Random();
        File result;
        do
        {
            int i = rand.nextInt(Integer.MAX_VALUE);
//            System.out.println(i);
            String desFile = srcFile.replaceAll("\\.", i + ".");
//            System.out.println(desFile);
            result = new File(desFile);
        } while (result.exists());
        return result;

    }

    public static void main(String[] args) throws IOException {
        String path = "f:\\work\\.java";
        operateFile(path, (IStringOperator) (String src)->{
            src = src.replaceAll(";", ";\r\n");
            return src + "\n\n";
        });
    }
}
