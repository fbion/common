package designpattern.factory.example2.store;

import designpattern.factory.example2.pizza.CalmPizza;
import designpattern.factory.example2.pizza.CheesePizza;
import designpattern.factory.example2.pizza.PepperoniPizza;
import designpattern.factory.example2.pizza.Pizza;
import designpattern.factory.example2.pizza.VeggiePizza;
import designpattern.factory.example2.pizza.ingredient.PizzaIngredientFactory;
import designpattern.factory.example2.pizza.ingredient.impl.NYPizzaIngredientFactory;

/**
 * Created by Administrator on 2016/5/9.
 * HeadFirst设计模式中的抽象工厂模式例子
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String item) {
        Pizza pizza = null;
        PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
        if(item.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
            pizza.setName("New York Style Cheese Pizza");
        } else if(item.equals("veggie")) {
            pizza = new VeggiePizza(ingredientFactory);
            pizza.setName("New York Style Veggie Pizza");
        } else if(item.equals("calm")) {
            pizza = new CalmPizza(ingredientFactory);
            pizza.setName("New York Style Calm Pizza");
        } else if(item.equals("pepperoni")) {
            pizza = new PepperoniPizza(ingredientFactory);
            pizza.setName("New York Style Pepperoni Pizza");
        }
        return pizza;
    }
}
