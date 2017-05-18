package designpattern.factory.example1.store;

import designpattern.factory.example1.pizza.Pizza;
import designpattern.factory.example1.pizza.nystyle.NYStyleCalmPizza;
import designpattern.factory.example1.pizza.nystyle.NYStyleCheesePizza;
import designpattern.factory.example1.pizza.nystyle.NYStylePepperoniPizza;
import designpattern.factory.example1.pizza.nystyle.NYStyleVeggiePizza;

/**
 * Created by Administrator on 2016/5/7.
 */
public class NYStylePizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if(type.equals("cheese")) {
            pizza = new NYStyleCheesePizza();
        } else if(type.equals("pepperoni")) {
            pizza = new NYStylePepperoniPizza();
        } else if(type.equals("calm")) {
            pizza = new NYStyleCalmPizza();
        } else if(type.equals("veggie")) {
            pizza = new NYStyleVeggiePizza();
        }
        return pizza;
    }
}
