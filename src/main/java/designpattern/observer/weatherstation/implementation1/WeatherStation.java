package designpattern.observer.weatherstation.implementation1;

import designpattern.observer.weatherstation.implementation1.impl.CurrentConditionsDisplay;
import designpattern.observer.weatherstation.implementation1.impl.WeatherData;

/**
 * Created by Administrator on 2016/5/4.
 */
public class WeatherStation {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
