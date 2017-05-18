package thinking.chapter18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

/**
 * Created by Administrator on 2016/3/22.
 */
public class FileOutputShortCut {
    static String file = "F:\\idea-projects\\test\\src\\thinking\\chapter17\\BasicFileOutput.out";
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new StringReader(BufferedInputFile.read("F:\\idea-projects\\test\\src\\thinking\\chapter17\\BasicFileOutput.java")));
        PrintWriter out = new PrintWriter(file);
        int lineCount = 1;
        String s;
        while((s=in.readLine()) != null)
        {
            out.println(lineCount++ + " : " + s);
        }
        out.close();
        System.out.println(BufferedInputFile.read(file));
    }
}
