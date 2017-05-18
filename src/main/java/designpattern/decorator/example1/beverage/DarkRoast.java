package designpattern.decorator.example1.beverage;

import designpattern.decorator.example1.Beverage;

/**
 * Created by Administrator on 2016/5/6.
 */
//Dark Roast焦炒啡咖
public class DarkRoast extends Beverage {
    public DarkRoast() {
        description = "DarkRoast";
    }

    @Override
    public double cost() {
        return 1.49;
    }
}
