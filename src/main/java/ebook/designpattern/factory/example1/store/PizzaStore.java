package ebook.designpattern.factory.example1.store;

import ebook.designpattern.factory.example1.pizza.Pizza;

/**
 * Created by Administrator on 2016/5/7.
 */
public abstract class PizzaStore {

    public Pizza orderPizza(String type) {
        Pizza pizza;

        pizza = createPizza(type);
        return pizza.product();
    }

    public abstract Pizza createPizza(String type);
}
