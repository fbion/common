package ebook.designpattern.templatemethod.example1;

/**
 * Created by Administrator on 2016/5/23.
 */
public class Coffee extends CaffeineBeverage {
    @Override
    public void brew() {
        System.out.println("Dripping coffee through filter");
    }


    @Override
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
}
