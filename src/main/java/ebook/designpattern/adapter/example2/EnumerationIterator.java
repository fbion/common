package ebook.designpattern.adapter.example2;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/5/20.
 * HeadFirst设计模式中的适配器模式例子
 */
public class EnumerationIterator implements Iterator {
    Enumeration enumeration;

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public Object next() {
        return enumeration.nextElement();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
