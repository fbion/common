package designpattern.decorator.example1.beverage;

import designpattern.decorator.example1.Beverage;

/**
 * Created by Administrator on 2016/5/6.
 */
//Espresso浓啡咖
public class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
