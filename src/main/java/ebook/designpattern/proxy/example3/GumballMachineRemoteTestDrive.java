package ebook.designpattern.proxy.example3;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/**
 * 描述： HeadFirst设计模式中的远程调用代理模式例子<br>
 * 创建时间: 2017/5/2317:51 <br>
 *
 * @author 周志辉
 */
public class GumballMachineRemoteTestDrive {

    public static void main(String[] args) {
        GumballMachine gumballMachine = null;
        int count;
        if(args.length < 2) {
            System.out.println("GumballMachine <name> <inventory>.");
            System.exit(1);
        }

        try {
            count = Integer.parseInt(args[1]);
            gumballMachine = new GumballMachine(count, args[0]);
            Naming.rebind("//" + args[0] + "/gumballMachine", gumballMachine);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
