package ebook.thinking.chapter18.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * Created by Administrator on 2016/3/22.
 */
public class BufferToText {
    private static final int BSZIE = 1024;

    public static void main(String[] args) throws IOException {
        String fileName = "f:\\data2.txt";
        FileChannel fc = new FileOutputStream(fileName).getChannel();
        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();
        fc = new FileInputStream(fileName).getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSZIE);
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
        buffer.rewind();
        String encoding = System.getProperty("file.encoding");
        System.out.println("Decode using " + encoding + " : " + Charset.forName(encoding).decode(buffer));
        fc = new FileOutputStream(fileName).getChannel();
        fc.write(ByteBuffer.wrap("Some text ".getBytes("UTF-16BE")));
        fc.close();
        fc = new FileInputStream(fileName).getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
        fc = new FileOutputStream(fileName).getChannel();
        buffer = ByteBuffer.allocate(24);
        buffer.asCharBuffer().put("Some text ");
        fc.write(buffer);
        fc.close();
        fc = new FileInputStream(fileName).getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
    }
}
