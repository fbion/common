package ebook.designpattern.state;


/**
 * Created by Administrator on 2016/5/25.
 */
public class GumballMachine {

    /**
     * 糖果售空状态
     */
    State soldOutState;

    /**
     * 未投币状态
     */
    State noQuarterState;

    /**
     * 已投币状态
     */
    State hasQuarterState;

    /**
     * 售出糖果状态
     */
    State soldState;


    /**
     * 糖果售空状态
     */
    State winnerState;

    /**
     * 状态
     */
    State state = soldOutState;

    /**
     * 糖果数
     */
    int count = 0;

    public GumballMachine(int count) {
        soldOutState = new SoldOutState(this);
        noQuarterState = new NoQuarterState(this);
        hasQuarterState = new HasQuarterState(this);
        soldState = new SoldState(this);
        winnerState = new WinnerState(this);
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

    public State getState() {
        return state;
    }

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
