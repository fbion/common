package designpattern.proxy.example3;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 描述： HeadFirst设计模式中的远程调用代理模式例子<br>
 * 创建时间: 2017/5/2317:58 <br>
 *
 * @author 周志辉
 */
public class GumballMonitorTestDriver {

    public static void main(String[] args) {
        String[] location = {"rmi://127.0.0.1/gumballMachine"};
        GumballMonitor[] monitor = new GumballMonitor[location.length];
        for (int i = 0; i < location.length; i++) {
            try {
                GumballMachineRemote machine = (GumballMachineRemote) Naming.lookup(location[i]);
                monitor[i] = new GumballMonitor(machine);
                System.out.println(monitor[i]);
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        for (GumballMonitor gumballMonitor : monitor) {
            gumballMonitor.report();
        }
    }
}
