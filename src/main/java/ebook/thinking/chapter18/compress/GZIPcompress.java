package ebook.thinking.chapter18.compress;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Administrator on 2016/3/24.
 */
public class GZIPcompress {
    public static void main(String[] args) throws IOException {
        args = new String[]{"f:\\1.txt"};
        BufferedReader in = new BufferedReader(new FileReader(new File(args[0])));
        BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream("f:\\test.gz")));
        System.out.println("Write File.");
        int c;
        while((c = in.read()) != -1)
        {
            out.write(c);
        }
        in.close();
        out.close();
        System.out.println("Read File.");
        BufferedReader in2 = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream("f:\\test.gz"))));
        String s;
        while((s = in2.readLine()) != null)
        {
            System.out.println(s);
        }
    }
}
