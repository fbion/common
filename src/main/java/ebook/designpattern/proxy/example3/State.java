package ebook.designpattern.proxy.example3;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/25.
 */
public interface State extends Serializable {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
}
