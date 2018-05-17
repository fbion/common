package ebook.designpattern.factory.example2.pizza.ingredient.impl;

import ebook.designpattern.factory.example2.pizza.ingredient.Calms;
import ebook.designpattern.factory.example2.pizza.ingredient.Cheese;
import ebook.designpattern.factory.example2.pizza.ingredient.Dough;
import ebook.designpattern.factory.example2.pizza.ingredient.Pepperoni;
import ebook.designpattern.factory.example2.pizza.ingredient.PizzaIngredientFactory;
import ebook.designpattern.factory.example2.pizza.ingredient.Sauce;
import ebook.designpattern.factory.example2.pizza.ingredient.Veggies;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.Sauce.MarinaraSauce;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.calms.FreshCalms;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.cheese.ReggianoCheese;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.dough.ThinCrustDough;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.pepperoni.SlicedPepperoni;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.veggies.Garlic;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.veggies.Mushroom;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.veggies.Onion;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.veggies.RedPepper;

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
