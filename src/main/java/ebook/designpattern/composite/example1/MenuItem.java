package ebook.designpattern.composite.example1;

import java.util.Iterator;

/**
 * Created by Administrator on 2016/5/24.
 */
public class MenuItem extends MenuComponent {
    String name;
    String description;
    boolean vegetarian;
    double price;

    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public boolean isVegetarian() {
        return vegetarian;
    }

    @Override
    public void print() {
        System.out.print(" " + name);
        if(vegetarian) {
            System.out.print(" (v) ");
        }
        System.out.print("," + price);
        System.out.println("   -----" + description);
    }

    public Iterator<MenuComponent> createIterator() {
        return new NullIterator();
    }
}
