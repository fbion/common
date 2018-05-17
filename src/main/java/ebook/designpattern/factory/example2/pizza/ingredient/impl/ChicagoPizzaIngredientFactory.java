package ebook.designpattern.factory.example2.pizza.ingredient.impl;

import ebook.designpattern.factory.example2.pizza.ingredient.Calms;
import ebook.designpattern.factory.example2.pizza.ingredient.Cheese;
import ebook.designpattern.factory.example2.pizza.ingredient.Dough;
import ebook.designpattern.factory.example2.pizza.ingredient.Pepperoni;
import ebook.designpattern.factory.example2.pizza.ingredient.PizzaIngredientFactory;
import ebook.designpattern.factory.example2.pizza.ingredient.Sauce;
import ebook.designpattern.factory.example2.pizza.ingredient.Veggies;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.Sauce.PlumTomatoSauce;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.calms.FrozenCalms;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.cheese.MozzarellaCheese;
import ebook.designpattern.factory.example2.pizza.ingredient.impl.pepperoni.SlicedPepperoni;

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
