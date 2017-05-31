package advance.NIO;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 描述： <br>
 * 创建时间: 2017/5/2711:30 <br>
 *
 * @author 周志辉
 */
public class TestFileChannel {

    public static void main(String[] args) throws IOException {
        RandomAccessFile aFile = new RandomAccessFile("d:\\d\\1.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            //读转写
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            //clear或compact
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
