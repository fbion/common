package designpattern.proxy.example3;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 描述： <br>
 * 创建时间: 2017/5/2317:45 <br>
 *
 * @author 周志辉
 */
public interface GumballMachineRemote extends Remote {
    int getCount() throws RemoteException;
    String getLocation() throws RemoteException;
    State getState() throws RemoteException;
}
