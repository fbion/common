package designpattern.factory.example1;

import designpattern.factory.example1.pizza.Pizza;
import designpattern.factory.example1.store.ChicagoStylePizzaStore;
import designpattern.factory.example1.store.NYStylePizzaStore;
import designpattern.factory.example1.store.PizzaStore;

/**
 * Created by Administrator on 2016/5/7.
 */
public class PizzaTestDrive {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYStylePizzaStore();
        PizzaStore chicagoStore = new ChicagoStylePizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        pizza = chicagoStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");
    }
}
