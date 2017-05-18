package test.socket;

import java.io.*;

/**
 * Created by Administrator on 2016/7/15.
 */
public class TestSerial {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        ObjectOutputStream oos = new ObjectOutputStream(System.out);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("f:\\333.txt"));
        User u = new User();
        u.setName("张三");
        u.setPass("123");
        oos.writeObject(u);
        System.out.println("----------------");
        User u2 = new User();
        u2.setName("李四");
        u2.setPass("456");
        oos.writeObject(u2);
        oos.flush();
        oos.close();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("f:\\333.txt"));
        User u1 = (User) ois.readObject();
        System.out.println(u1.getName());
        System.out.println(u1.getPass());
        User u3 = (User) ois.readObject();
        System.out.println(u1.getName());
        System.out.println(u1.getPass());
        ois.close();
    }
}
