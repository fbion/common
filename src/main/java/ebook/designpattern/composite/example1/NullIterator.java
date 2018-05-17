package ebook.designpattern.composite.example1;

import java.util.Iterator;

/**
 * Created by Administrator on 2016/5/24.
 */
public class NullIterator implements Iterator {
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
