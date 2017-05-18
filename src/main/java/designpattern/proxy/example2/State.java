package designpattern.proxy.example2;

/**
 * Created by Administrator on 2016/5/25.
 */
public interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
}
