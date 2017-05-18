package designpattern.factory.example1.store;

import designpattern.factory.example1.pizza.Pizza;
import designpattern.factory.example1.pizza.chicago.ChicagoStyleCalmPizza;
import designpattern.factory.example1.pizza.chicago.ChicagoStyleCheesePizza;
import designpattern.factory.example1.pizza.chicago.ChicagoStylePepperoniPizza;
import designpattern.factory.example1.pizza.chicago.ChicagoStyleVeggiePizza;

/**
 * Created by Administrator on 2016/5/7.
 */
public class ChicagoStylePizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if(type.equals("cheese")) {
            pizza = new ChicagoStyleCheesePizza();
        } else if(type.equals("pepperoni")) {
            pizza = new ChicagoStylePepperoniPizza();
        } else if(type.equals("calm")) {
            pizza = new ChicagoStyleCalmPizza();
        } else if(type.equals("veggie")) {
            pizza = new ChicagoStyleVeggiePizza();
        }
        return pizza;
    }
}
