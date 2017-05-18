package designpattern.decorator.example1.seasoning;

import designpattern.decorator.example1.Beverage;
import designpattern.decorator.example1.CondimentDecorator;

/**
 * Created by Administrator on 2016/5/6.
 */
public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Whip";
    }

    @Override
    public double cost() {
        return .2 + beverage.cost();
    }
}