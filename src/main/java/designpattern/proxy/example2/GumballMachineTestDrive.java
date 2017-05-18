package designpattern.proxy.example2;

/**
 * Created by Administrator on 2016/5/25.
 */
public class GumballMachineTestDrive {
    public static void main(String[] args) {
        int count = 5;

        GumballMachine gumballMachine = new GumballMachine(5, "Nanjing");
        GumballMonitor gumballMonitor = new GumballMonitor(gumballMachine);

        gumballMonitor.report();
    }
}
