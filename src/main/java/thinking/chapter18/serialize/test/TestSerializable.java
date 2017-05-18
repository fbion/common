package thinking.chapter18.serialize.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2016/3/24.
 */
public class TestSerializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Test t = new Test();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("f:\\Test.out"));
        out.writeObject("Test storage\n");
        out.writeObject(t);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("f:\\Test.out"));
        String s = (String)in.readObject();
        Test t2 = (Test)in.readObject();
        System.out.printf(s + "w2 = " + t2);
    }
}
