package designpattern.proxy.example3;

import java.rmi.RemoteException;

/**
 * Created by Administrator on 2016/5/26.
 */
public class GumballMonitor {
    GumballMachineRemote gumballMachine;

    public GumballMonitor(GumballMachineRemote gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void report() {
        try {
            System.out.println("Gumball Machine: " + gumballMachine.getLocation());
            System.out.println("Gumball inventory: " + gumballMachine.getCount() + " gumballs");
            System.out.println("Gumball state: " + gumballMachine.getState().getClass().getSimpleName());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
