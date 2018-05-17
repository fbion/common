package ebook.designpattern.decorator.example1.seasoning;

import ebook.designpattern.decorator.example1.Beverage;
import ebook.designpattern.decorator.example1.CondimentDecorator;

/**
 * Created by Administrator on 2016/5/6.
 */
//Mocha穆哈咖啡
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",Mocha";
    }

    @Override
    public double cost() {
        return .2 + beverage.cost();
    }
}
