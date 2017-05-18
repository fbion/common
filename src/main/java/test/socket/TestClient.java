package test.socket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2016/7/15.
 */
public class TestClient {
    Socket s;
    User u;
    ObjectOutputStream oos;
    public static void main(String[] args) {
        TestClient test = new TestClient();
    }

    public TestClient() {
        try{
            s = new Socket("127.0.0.1", 9999);
            oos = new ObjectOutputStream(s.getOutputStream());
            u = new User();
            u.setName("张三");
            u.setPass("123");
            oos.writeObject(u);
            u.setName("李四");
            u.setPass("456");
            oos.writeObject(u);
            System.out.println(u.getName() + " : " + u.getPass());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                oos.writeObject(u);
                if(oos != null) {
                    oos.close();
                }
                if(s != null) {
                    s.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
