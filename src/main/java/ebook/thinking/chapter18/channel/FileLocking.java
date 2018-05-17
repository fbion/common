package ebook.thinking.chapter18.channel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/3/23.
 */
public class FileLocking {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileOutputStream fos = new FileOutputStream("F:\\file.txt");
        FileLock fl = fos.getChannel().tryLock();
        if(fl != null)
        {
            System.out.println("Locked File");
            TimeUnit.SECONDS.sleep(1);
            fl.release();
            System.out.println("Release Lock");
        }
        fos.close();
    }
}
