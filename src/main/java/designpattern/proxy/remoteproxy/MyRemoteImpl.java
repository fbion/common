package designpattern.proxy.remoteproxy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Administrator on 2016/5/26.
 * HeadFirst设计模式中的远程调用代理模式例子
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    public MyRemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Server says, Hey.";
    }

    public static void main(String[] args) {
        try {
            MyRemote service = new MyRemoteImpl();
            Naming.rebind("RemoteHello", service);

        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
