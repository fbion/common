package ebook.designpattern.multiple.example1.impl;

import ebook.designpattern.multiple.example1.Observer;
import ebook.designpattern.multiple.example1.QuackObservable;
import java.util.HashSet;
import java.util.Set;

/**
 * 描述： <br>
 * 创建时间: 2017/7/2017:28 <br>
 *
 * @author 周志辉
 */
public abstract class BaseDuck implements QuackObservable{
    Set<Observer> observers = new HashSet<>();


    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }


    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }


    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.doSomething();
        }
    }
}
