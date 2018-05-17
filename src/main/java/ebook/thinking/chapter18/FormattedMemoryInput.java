package ebook.thinking.chapter18;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/22.
 */
public class FormattedMemoryInput {
    public static void main(String[] args) {
        try
        {
            DataInputStream in = new DataInputStream(new ByteArrayInputStream(BufferedInputFile.read("F:\\idea-projects\\test\\src\\thinking\\chapter17\\FormattedMemoryInput.java").getBytes()));
            while(true)
            {
                System.out.print((char)in.readByte());
            }
        } catch (EOFException e) {
            System.out.println();
            e.printStackTrace(System.err);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
