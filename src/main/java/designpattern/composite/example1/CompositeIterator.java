package designpattern.composite.example1;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by Administrator on 2016/5/24.
 */
public class CompositeIterator implements Iterator {
    Stack<Iterator<MenuComponent>> stack = new Stack();

    public CompositeIterator(Iterator iterator) {
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if(stack.empty()) {
            return false;
        } else {
            Iterator iterator = stack.peek();
            if(!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public MenuComponent next() {
        if(hasNext()) {
            Iterator<MenuComponent> iterator = stack.peek();
            MenuComponent menuComponent = iterator.next();
            if(menuComponent instanceof Menu) {
                stack.push((menuComponent).createIterator());
            }
            return menuComponent;
        }
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
