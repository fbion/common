package ebook.designpattern.iterator.example1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/5/24.
 */
public class PancakeHouseMenu implements Menu {
    String menuName;
    ArrayList<MenuItem> menuItems;

    public PancakeHouseMenu() {
        this("BREAKFAST");
    }

    public PancakeHouseMenu(String menuName) {
        this.menuName = menuName;
        this.menuItems = new ArrayList<>();
        addItem("K&B's Pancake Breakfast", "Pancakes with scrambled egss, and toast", true, 2.99);
    }

    @Override
    public String getMenuName() {
        return menuName;
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    public ArrayList getItems() {
        return menuItems;
    }

    public Iterator createIterator() {
        return menuItems.iterator();
    }
}
