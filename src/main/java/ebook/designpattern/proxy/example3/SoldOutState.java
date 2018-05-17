package ebook.designpattern.proxy.example3;

/**
 * Created by Administrator on 2016/5/25.
 */
public class SoldOutState implements State {
    transient GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Sorry, gumballs are sold out");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("You have not inserted a quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("You turned,but there is no quarter");
        System.out.println("Sorry, gumballs are sold out");
    }

    @Override
    public void dispense() {
        System.out.println("Sorry, gumballs are sold out");
        System.out.println("You need to pay first");
    }
}
