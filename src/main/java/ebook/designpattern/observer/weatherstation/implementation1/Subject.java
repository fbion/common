package ebook.designpattern.observer.weatherstation.implementation1;

/**
 * Created by Administrator on 2016/5/4.
 */
public interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}
