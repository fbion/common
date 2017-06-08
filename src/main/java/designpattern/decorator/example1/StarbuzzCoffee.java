package designpattern.decorator.example1;

import designpattern.decorator.example1.beverage.DarkRoast;
import designpattern.decorator.example1.beverage.Espresso;
import designpattern.decorator.example1.beverage.HouseBlend;
import designpattern.decorator.example1.seasoning.Mocha;
import designpattern.decorator.example1.seasoning.Soy;
import designpattern.decorator.example1.seasoning.Whip;

/**
 * Created by Administrator on 2016/5/6.
 * HeadFirst设计模式中的装饰模式例子
 */
//Starbuzz Coffee星巴克咖啡
public class StarbuzzCoffee {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $ " + beverage.cost());

        Beverage beverage1 = new DarkRoast();
        beverage1 = new Mocha(beverage1);
        beverage1 = new Mocha(beverage1);
        beverage1 = new Whip(beverage1);
        System.out.println(beverage1.getDescription() + " $ " + beverage1.cost());

        Beverage beverage2 = new HouseBlend();
        beverage2 = new Soy(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $ " + beverage2.cost());
    }
}
