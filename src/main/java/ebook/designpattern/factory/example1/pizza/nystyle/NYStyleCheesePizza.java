package ebook.designpattern.factory.example1.pizza.nystyle;

import ebook.designpattern.factory.example1.pizza.CheesePizza;

/**
 * Created by Administrator on 2016/5/7.
 */
public class NYStyleCheesePizza extends CheesePizza {
    public NYStyleCheesePizza() {
        this.name = "NY style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";
        toppings.add("Grated Reggiano Cheese");
    }
}
