package designpattern.iterator.example1;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public class Waitress {
    List<Menu> menus;

    public Waitress(List<Menu> menus) {
        this.menus = menus;
    }

    public void printMenu() {
        System.out.println("MENU----");
        for (Menu menu : menus) {
            System.out.println("\n" + menu.getMenuName());
            printMenu(menu.createIterator());
        }
    }

    public void printMenu(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            System.out.println(menuItem);
        }
    }
}
