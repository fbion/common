package ebook.designpattern.iterator.example1;

/**
 * Created by Administrator on 2016/5/24.
 */
public class MenuItem {
    String name;
    String description;
    boolean vegatarian;
    double price;

    public MenuItem(String name, String description, boolean vegatarian, double price) {
        this.name = name;
        this.description = description;
        this.vegatarian = vegatarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVegatarian() {
        return vegatarian;
    }

    @Override
    public String toString() {
        return name + " , " + price + " -- " + description;
    }
}
