package test.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/20.
 */
public class Test8 {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket s = new Socket("127.0.0.1", 9090);
        InetAddress addres = s.getInetAddress();
        System.out.println(addres);

        BufferedReader sin=new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line = sin.readLine();
        System.out.println(line);

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        out.write(line);
        out.flush();
        out.close();

        s.close();
    }
}
