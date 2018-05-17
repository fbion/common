package ebook.designpattern.factory.example1;

import ebook.designpattern.factory.example1.pizza.Pizza;
import ebook.designpattern.factory.example1.store.ChicagoStylePizzaStore;
import ebook.designpattern.factory.example1.store.NYStylePizzaStore;
import ebook.designpattern.factory.example1.store.PizzaStore;

/**
 * Created by Administrator on 2016/5/7.
 * HeadFirst设计模式中的工厂方法模式例子
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