package designpattern.proxy.example3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Administrator on 2016/5/25.
 */
public class GumballMachine extends UnicastRemoteObject implements GumballMachineRemote {
    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;
    State winnerState;

    State state = soldOutState;
    int count = 0;
    String location;

    public GumballMachine(int count, String location) throws RemoteException{
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);
        this.location = location;
        if((this.count = count) > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    public void dispense() {
        state.dispense();
    }

    void releaseBall() {
        System.out.println("A gumball comes rolling out of the slot....\n");
        if(count != 0) {
            count--;
        }
    }

    public void refill(int count) {
        if(count > 0) {
            setCount(count);
            setState(getNoQuarterState());
        } else {
            System.out.println("No gumballs filled.");
        }
    }

    @Override
    public String getLocation() {
        return location;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "There are " + count + " gumballs left.";
    }

    public void setCount(int count) {
        this.count = count;
    }
}
