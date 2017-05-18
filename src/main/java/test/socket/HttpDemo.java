package test.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/29.
 */

public class HttpDemo extends Thread
{
    private ServerSocket serverSocket;
    private int port = 80;

    public void run()
    {
//        System.out.println("Srver start");
        String[] MIME_TYPES = {
            "text/html",
            "image/jpeg",
            "image/png",
            "image/gif",
            "application/javascript",
            "text/css",
        };
        try {
            serverSocket = new ServerSocket(port);
            while(true) {
                Socket connectedClient = serverSocket.accept();
                DataInputStream inStream = new DataInputStream(connectedClient.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inStream));
                String str = bufferedReader.readLine();
                ArrayList<String> headers = new ArrayList();
                String encoding = "utf-8";
                while(str != null) {
                    System.out.println(str);
                    headers.add(str);
                    if (str.isEmpty()) {
                        //analysis headers and response http request
                        String firstLine = headers.get(0);
                        String parts[] = firstLine.split(" ");
                        String responseHeader;
                        String responseBody;
                        String file;
                        responseHeader = "HTTP/1.1 200 OK\r\n";
                        DataOutputStream out = new DataOutputStream(connectedClient.getOutputStream());
                        if (parts[1].equals("/")) {
                            file = "./index.html";
                        }  else {
                            file = "." + parts[1];
                        }

                        String mimeType;
                        if (file.endsWith(".html")) {
                            mimeType = MIME_TYPES[0];
                        } else if(file.endsWith(".jpg")) {
                            mimeType = MIME_TYPES[1];
                        } else if(file.endsWith(".png")) {
                            mimeType = MIME_TYPES[2];
                        } else if(file.endsWith(".gif")) {
                            mimeType = MIME_TYPES[3];
                        } else if(file.endsWith(".css")) {
                            mimeType = MIME_TYPES[5];
                        } else if(file.endsWith(".js")) {
                            mimeType = MIME_TYPES[4];
                        } else {
                            mimeType = MIME_TYPES[0];
                        }

                        try {
                            File fl = new File(file);
                            Long len = fl.length();
                            responseHeader += "Content-Length:" + len + "\r\n";
                            responseHeader += "Content-Type:" + mimeType;
                            byte[] buf = new byte[len.intValue()];
                            FileInputStream flin = new FileInputStream(file);
                            flin.read(buf, 0, len.intValue());
                            responseBody = new String(buf, encoding);
                        } catch(Exception e) {
                            connectedClient.close();
                            break;
                        }

                        out.writeBytes(responseHeader + "\r\n\r\n" + responseBody);
                        connectedClient.close();
                        break;
                    }
                    str = bufferedReader.readLine();
                }
            }
        } catch(Exception e) {
            //do nothing
        }
    }

    public static void main(String[] args) {
        new HttpDemo().run();
    }
}
