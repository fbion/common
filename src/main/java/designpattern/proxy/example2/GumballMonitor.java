package designpattern.proxy.example2;

/**
 * Created by Administrator on 2016/5/26.
 */
public class GumballMonitor {
    GumballMachine gumballMachine;

    public GumballMonitor(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void report() {
        System.out.println("Gumball Machine: " + gumballMachine.getLocation());
        System.out.println("Gumball inventory: " + gumballMachine.getCount() + " gumballs");
        System.out.println("Gumball state: " + gumballMachine.getState().getClass().getSimpleName());
    }
}
