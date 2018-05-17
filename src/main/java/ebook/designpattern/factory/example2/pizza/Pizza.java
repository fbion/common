package ebook.designpattern.factory.example2.pizza;

import ebook.designpattern.factory.example2.pizza.ingredient.Calms;
import ebook.designpattern.factory.example2.pizza.ingredient.Cheese;
import ebook.designpattern.factory.example2.pizza.ingredient.Dough;
import ebook.designpattern.factory.example2.pizza.ingredient.Pepperoni;
import ebook.designpattern.factory.example2.pizza.ingredient.Sauce;
import ebook.designpattern.factory.example2.pizza.ingredient.Veggies;

/**
 * Created by Administrator on 2016/5/9.
 */
public abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Veggies veggies[];
    Cheese cheese;
    Pepperoni pepperoni;
    Calms calm;
    protected abstract void prepare();
    protected void bake() {
        System.out.println("Bake for 25 minutes at  350.");
    }

    protected void cut() {
        System.out.println("Cutting the pizza into diagonal slices.");
    }

    protected void box() {
        System.out.println("Place pizza in official PizzaStore box.");
    }

    public Pizza product() {
        prepare();
        bake();
        cut();
        box();
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
