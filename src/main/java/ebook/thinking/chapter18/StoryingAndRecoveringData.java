package ebook.thinking.chapter18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/22.
 */
public class StoryingAndRecoveringData {
    public static void main(String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("F:\\idea-projects\\test\\src\\thinking\\chapter17\\Data.txt")));
        out.writeDouble(3.1415926);
        out.writeUTF("That was PI");
        out.writeDouble(1.41413);
        out.writeUTF("Square root of 2");
        out.close();
        DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("F:\\idea-projects\\test\\src\\thinking\\chapter17\\Data.txt")));
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
        System.out.println(in.readDouble());
        System.out.println(in.readUTF());
    }
}
