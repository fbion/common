package designpattern.proxy.remoteproxy;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Administrator on 2016/5/26.
 */
public interface MyRemote extends Remote{
    public String sayHello() throws RemoteException;
}
