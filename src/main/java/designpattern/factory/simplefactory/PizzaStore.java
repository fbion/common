package designpattern.factory.simplefactory;

import designpattern.factory.simplefactory.pizza.Pizza;

/**
 * Created by Administrator on 2016/5/7.
 */
public class PizzaStore {
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza (String type) {
        Pizza pizza;
        pizza = factory.createPizza(type);
        return pizza.product();
    }
}
