package designpattern.multiple.example1.factory;

import designpattern.multiple.example1.Quackable;
import designpattern.multiple.example1.impl.DuckCall;
import designpattern.multiple.example1.impl.MarllardDuck;
import designpattern.multiple.example1.impl.RedheadDuck;
import designpattern.multiple.example1.impl.RubberDuck;

/**
 * 描述： <br>
 * 创建时间: 2017/7/1817:49 <br>
 *
 * @author 周志辉
 */
public class DuckFatory extends AbstractDuckFactory {

    @Override
    public Quackable createMarllardDuck() {
        return new MarllardDuck();
    }


    @Override
    public Quackable createRedheadDuck() {
        return new RedheadDuck();
    }


    @Override
    public Quackable createDuckCall() {
        return new DuckCall();
    }


    @Override
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
