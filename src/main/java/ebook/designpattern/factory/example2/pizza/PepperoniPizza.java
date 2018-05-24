package ebook.designpattern.factory.example2.pizza;

import ebook.designpattern.factory.example2.pizza.ingredient.PizzaIngredientFactory;

/**
 * Created by Administrator on 2016/5/7.
 */
public class PepperoniPizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public PepperoniPizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    protected void prepare() {
        System.out.println("Preparing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
    }
}