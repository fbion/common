package thinking.chapter18.serialize.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2016/3/24.
 */
public class FreezeAlien {
    public static void main(String[] args) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("f:\\x.file"));
        Alien quellek = new Alien();
        out.writeObject(quellek);
    }
}
