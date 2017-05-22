package designpattern.state;

/**
 * Created by Administrator on 2016/5/25.
 */
public interface State {

    /**
     * 投币
     */
    void insertQuarter();

    /**
     * 退币
     */
    void ejectQuarter();

    /**
     * 转杆
     */
    void turnCrank();

    /**
     * 发放糖果
     */
    void dispense();
}
