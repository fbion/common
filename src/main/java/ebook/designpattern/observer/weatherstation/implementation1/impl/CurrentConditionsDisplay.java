package ebook.designpattern.observer.weatherstation.implementation1.impl;

import ebook.designpattern.observer.weatherstation.implementation1.DisplayElment;
import ebook.designpattern.observer.weatherstation.implementation1.Observer;

/**
 * Created by Administrator on 2016/5/4.
 */
public class CurrentConditionsDisplay implements Observer, DisplayElment {
    private float temperature;
    private float humidity;
    private float pressure;
    private WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("CurrentConditions: " + temperature + "F degrees, " + humidity + "% humidity and pressure: ." + pressure);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }
}
