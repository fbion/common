package designpattern.decorator.example1;

/**
 * Created by Administrator on 2016/5/6.
 */
//Beverage饮料
public abstract class Beverage {
    protected String description = "unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
