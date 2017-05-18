package test.base;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/5/13.
 */
public class TestDoublePlus {
    public static void main(String[] args) {
        int a = 5;
        int b = 5;
        int x = a++ + b;
        System.out.println(a);
        System.out.println(b);

        int i = 5;
        int j = 5;
        j += i +++ j;
        System.out.println(i);
        System.out.println(j);
    }
}
