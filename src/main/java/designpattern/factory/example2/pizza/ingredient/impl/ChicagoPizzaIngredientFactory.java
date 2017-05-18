package designpattern.factory.example2.pizza.ingredient.impl;

import designpattern.factory.example2.pizza.ingredient.Calms;
import designpattern.factory.example2.pizza.ingredient.Cheese;
import designpattern.factory.example2.pizza.ingredient.Dough;
import designpattern.factory.example2.pizza.ingredient.Pepperoni;
import designpattern.factory.example2.pizza.ingredient.PizzaIngredientFactory;
import designpattern.factory.example2.pizza.ingredient.Sauce;
import designpattern.factory.example2.pizza.ingredient.Veggies;
import designpattern.factory.example2.pizza.ingredient.impl.Sauce.PlumTomatoSauce;
import designpattern.factory.example2.pizza.ingredient.impl.calms.FrozenCalms;
import designpattern.factory.example2.pizza.ingredient.impl.cheese.MozzarellaCheese;
import designpattern.factory.example2.pizza.ingredient.impl.pepperoni.SlicedPepperoni;

/**
 * Created by Administrator on 2016/5/9.
 */
public class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return null;
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        Veggies[] veggies = new Veggies[]{};
        return veggies;
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Calms createCalm() {
        return new FrozenCalms();
    }
}
