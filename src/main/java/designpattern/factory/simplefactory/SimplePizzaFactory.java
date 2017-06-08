package designpattern.factory.simplefactory;

import designpattern.factory.simplefactory.pizza.CalmPizza;
import designpattern.factory.simplefactory.pizza.CheesePizza;
import designpattern.factory.simplefactory.pizza.PepperoniPizza;
import designpattern.factory.simplefactory.pizza.Pizza;
import designpattern.factory.simplefactory.pizza.VeggiePizza;

/**
 * Created by Administrator on 2016/5/7.
 * HeadFirst设计模式中的简单工厂模式例子
 */
public class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if(type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if(type.equals("pepperoni")) {
            pizza = new PepperoniPizza();
        } else if(type.equals("calm")) {
            pizza = new CalmPizza();
        } else if(type.equals("veggie")) {
            pizza = new VeggiePizza();
        }
        return pizza;
    }
}
