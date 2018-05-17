package ebook.thinking.chapter18.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Administrator on 2016/3/22.
 */
public class TransferTo {
    public static void main(String[] args) throws IOException {
        if(args.length != 2)
        {
            System.out.println("arguments: sourcefile destfile");
            args = new String[]{
                    "f:\\1.txt",
                    "f:\\2.txt"
            };
//            System.exit(1);
        }
        FileChannel in = new FileInputStream(args[0]).getChannel(),
                out = new FileOutputStream(args[1]).getChannel();
//        in.transferTo(0, in.size(), out);
        out.transferFrom(in, 0, in.size());
    }
}
