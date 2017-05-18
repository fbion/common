package designpattern.factory.example1.pizza.chicago;

import designpattern.factory.example1.pizza.CheesePizza;

/**
 * Created by Administrator on 2016/5/7.
 */
public class ChicagoStyleCheesePizza extends CheesePizza {
    public ChicagoStyleCheesePizza() {
        this.name = "Chicago Sauce Deep Dish Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";
        toppings.add("Shredded Mozzarella Cheese");
    }

    protected void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
