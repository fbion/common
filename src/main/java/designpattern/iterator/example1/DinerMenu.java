package designpattern.iterator.example1;

import java.util.Iterator;

/**
 * Created by Administrator on 2016/5/24.
 */
public class DinerMenu implements Menu {
    String menuName;
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        this("LUNCH");
    }

    public DinerMenu(String menuName) {
        this.menuName = menuName;
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("A", "A", true, 2.99);
    }

    public String getMenuName() {
        return menuName;
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        if(numberOfItems >= MAX_ITEMS) {
            System.out.println("Menu full");
        } else{
            MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    public Iterator createIterator() {
        return new DinnerIterator(menuItems);
    }
}
