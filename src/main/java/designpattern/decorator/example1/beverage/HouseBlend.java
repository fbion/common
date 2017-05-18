package designpattern.decorator.example1.beverage;

import designpattern.decorator.example1.Beverage;

/**
 * Created by Administrator on 2016/5/6.
 */
//House Blend综合咖啡
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return .89;
    }
}
