package ebook.thinking.chapter18;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/22.
 */
public class TestEOF {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read("F:\\idea-projects\\test\\src\\thinking\\chapter17\\TestEOF.java").getBytes()));
        while(in.available() != 0)
        {
            System.out.print((char)in.readByte());
        }
    }
}
