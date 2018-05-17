package ebook.thinking.chapter18;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Administrator on 2016/3/22.
 */
public class MemoryInput {
    public static void main(String[] args) throws IOException {
        StringReader in = new StringReader(BufferedInputFile.read("F:\\idea-projects\\test\\src\\thinking\\chapter17\\MemoryInput.java"));
        int c;
        while((c = in.read()) != -1)
        {
            System.out.print((char)c);
        }
    }
}
