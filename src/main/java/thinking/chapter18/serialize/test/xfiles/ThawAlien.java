package thinking.chapter18.serialize.test.xfiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Administrator on 2016/3/24.
 */
public class ThawAlien {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("f:\\x.file"));
        Object mystery = in.readObject();
        System.out.println(mystery.getClass());
    }
}
