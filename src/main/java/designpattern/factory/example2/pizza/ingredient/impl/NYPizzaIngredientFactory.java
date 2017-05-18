package designpattern.factory.example2.pizza.ingredient.impl;

import designpattern.factory.example2.pizza.ingredient.Calms;
import designpattern.factory.example2.pizza.ingredient.Cheese;
import designpattern.factory.example2.pizza.ingredient.Dough;
import designpattern.factory.example2.pizza.ingredient.Pepperoni;
import designpattern.factory.example2.pizza.ingredient.PizzaIngredientFactory;
import designpattern.factory.example2.pizza.ingredient.Sauce;
import designpattern.factory.example2.pizza.ingredient.Veggies;
import designpattern.factory.example2.pizza.ingredient.impl.Sauce.MarinaraSauce;
import designpattern.factory.example2.pizza.ingredient.impl.calms.FreshCalms;
import designpattern.factory.example2.pizza.ingredient.impl.cheese.ReggianoCheese;
import designpattern.factory.example2.pizza.ingredient.impl.dough.ThinCrustDough;
import designpattern.factory.example2.pizza.ingredient.impl.pepperoni.SlicedPepperoni;
import designpattern.factory.example2.pizza.ingredient.impl.veggies.Garlic;
import designpattern.factory.example2.pizza.ingredient.impl.veggies.Mushroom;
import designpattern.factory.example2.pizza.ingredient.impl.veggies.Onion;
import designpattern.factory.example2.pizza.ingredient.impl.veggies.RedPepper;

/**
 * Created by Administrator on 2016/5/9.
 */
public class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }

    @Override
    public Veggies[] createVeggies() {
        Veggies[] veggies = new Veggies[]{ new Garlic(), new Onion(), new Mushroom(), new RedPepper() };
        return veggies;
    }

    @Override
    public Pepperoni createPepperoni() {
        return new SlicedPepperoni();
    }

    @Override
    public Calms createCalm() {
        return new FreshCalms();
    }
}
