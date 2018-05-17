package ebook.designpattern.observer.weatherstation.implementation1;

/**
 * Created by Administrator on 2016/5/4.
 */
public interface Observer {
    void update(float temperature, float humidity, float pressure);
}
