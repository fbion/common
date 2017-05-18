package designpattern.factory.example2.pizza;

import designpattern.factory.example2.pizza.ingredient.PizzaIngredientFactory;

/**
 * Created by Administrator on 2016/5/7.
 */
public class CalmPizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public CalmPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    protected void prepare() {
        System.out.println("Preparing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
        calm = ingredientFactory.createCalm();
    }
}
