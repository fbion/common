package ebook.thinking.chapter18.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/3/23.
 */
public class LargeMappedFiles {
    static int length = 0x8ffffff;

    public static void main(String[] args) throws IOException {
        MappedByteBuffer out = new RandomAccessFile("F:\\test.dat", "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0, length);
        for (int i = 0; i < length; i++) {
            out.put((byte)'x');
        }
        System.out.println("Finishing writing.");
        for (int i = length/2; i < length + 6; i++) {
            System.out.print((char)out.get(i));
        }
    }
}
