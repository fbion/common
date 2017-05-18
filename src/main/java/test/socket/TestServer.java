package test.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2016/7/15.
 */
public class TestServer {
    ServerSocket ss;
    Socket s;
    ObjectInputStream ois;

    public TestServer() {
        try {
            ss = new ServerSocket(9999);
            System.out.println("nihao");
            s = ss.accept();
            ois = new ObjectInputStream(s.getInputStream());
            while(true) {
                User user = (User) ois.readObject();
                System.out.println(user.getName());
                System.out.println(user.getPass());
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        TestServer test = new TestServer();
        System.out.println("end");
    }
}
class User implements Serializable{
    private String name;
    private String pass;

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}