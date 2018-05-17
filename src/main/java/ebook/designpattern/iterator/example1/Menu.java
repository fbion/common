package ebook.designpattern.iterator.example1;

import java.util.Iterator;

/**
 * Created by Administrator on 2016/5/24.
 */
public interface Menu {
    Iterator<MenuItem> createIterator();

    String getMenuName();
}
