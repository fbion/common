package designpattern.proxy.remoteproxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 2016/5/26.
 * HeadFirst设计模式中的远程调用代理模式例子
 */
public class MyRemoteClient {
    public void go() {
        try {
            MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");
            String s = service.sayHello();
            System.out.println(s);
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        new MyRemoteClient().go();
    }
}
