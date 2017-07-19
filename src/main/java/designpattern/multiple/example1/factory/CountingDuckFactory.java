package designpattern.multiple.example1.factory;

import designpattern.multiple.example1.Quackable;
import designpattern.multiple.example1.decorator.QuackCounter;
import designpattern.multiple.example1.impl.DuckCall;
import designpattern.multiple.example1.impl.MarllardDuck;
import designpattern.multiple.example1.impl.RedheadDuck;
import designpattern.multiple.example1.impl.RubberDuck;

/**
 * 描述： <br>
 * 创建时间: 2017/7/1817:50 <br>
 *
 * @author 周志辉
 */
public class CountingDuckFactory extends AbstractDuckFactory {

    @Override
    public Quackable createMarllardDuck() {
        return new QuackCounter(new MarllardDuck());
    }


    @Override
    public Quackable createRedheadDuck() {
        return new QuackCounter(new RedheadDuck());
    }


    @Override
    public Quackable createDuckCall() {
        return new QuackCounter(new DuckCall());
    }


    @Override
    public Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }
}
