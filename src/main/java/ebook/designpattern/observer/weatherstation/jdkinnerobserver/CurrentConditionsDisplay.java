package ebook.designpattern.observer.weatherstation.jdkinnerobserver;

import ebook.designpattern.observer.weatherstation.implementation1.DisplayElment;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Administrator on 2016/5/4.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElment {
    Observable observable;
    private float temperature;
    private float humidity;
    private float pressure;

    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData) {
            WeatherData weatherData = (WeatherData)o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            this.pressure = weatherData.getPressure();
            display();
        }
    }

    @Override
    public void display() {
        System.out.println("CurrentConditions: " + temperature + "F degrees , " + humidity + "% humidity and pressure: ." + pressure);
    }
}
