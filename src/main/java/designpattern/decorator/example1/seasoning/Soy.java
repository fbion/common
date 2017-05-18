package designpattern.decorator.example1.seasoning;

import designpattern.decorator.example1.Beverage;
import designpattern.decorator.example1.CondimentDecorator;

/**
 * Created by Administrator on 2016/5/6.
 */
//Soy 酱油
public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Soy";
    }

    @Override
    public double cost() {
        return .2 + beverage.cost();
    }
}
