package designpattern.proxy.example2;

/**
 * Created by Administrator on 2016/5/25.
 */
public class WinnerState implements State {
    GumballMachine gumballMachine;

    static final String stateName = "winnerState";

    public WinnerState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("Sorry, you already turned the crunk");
    }

    @Override
    public void turnCrank() {
        System.out.println("Turning twice doesn't get you another gumball!");
    }

    @Override
    public void dispense() {
        gumballMachine.releaseBall();
        if(gumballMachine.getCount() == 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            System.out.println("YOU'RE A WINNER!You get two gumballs for your quarter");
            gumballMachine.releaseBall();
            if(gumballMachine.getCount() <= 0) {
                System.out.println("Oops, out of gumballs");
                gumballMachine.setState(gumballMachine.getSoldOutState());
            } else {
                gumballMachine.setState(gumballMachine.getNoQuarterState());
            }
        }
    }

    @Override
    public String toString() {
        return stateName;
    }
}
