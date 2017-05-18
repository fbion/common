package designpattern.iterator.example1;

import java.util.Iterator;

/**
 * Created by Administrator on 2016/5/24.
 */
public class DinnerIterator implements Iterator {
    MenuItem[] menuItems;
    int position;

    public DinnerIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        if(position >= menuItems.length || menuItems[position] == null) {
            return false;
        }
        return true;
    }

    @Override
    public MenuItem next() {
        return menuItems[position++];
    }

    @Override
    public void remove() {
        if(position <= 0) {
            throw new IllegalStateException("You can't remove an item until you have done ta least once next()");
        } else {
            if(menuItems[position] != null) {
                int i;
                for (i = 0; i < menuItems.length || menuItems[position + i] ==null; i++) {
                    menuItems[position - 1 + i] = menuItems[position + i];
                }
                menuItems[position - 1 + i] = null;
            }
        }
    }
}
