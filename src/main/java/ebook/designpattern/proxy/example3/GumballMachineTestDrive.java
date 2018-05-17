package ebook.designpattern.proxy.example3;

import java.rmi.RemoteException;

/**
 * Created by Administrator on 2016/5/25.
 * HeadFirst设计模式中的远程调用代理模式例子
 */
public class GumballMachineTestDrive {
    public static void main(String[] args) throws RemoteException {
        int count = 5;

        GumballMachine gumballMachine = new GumballMachine(5, "Nanjing");
        GumballMonitor gumballMonitor = new GumballMonitor(gumballMachine);

        gumballMonitor.report();
    }
}
