package designpattern.templatemethod.example1;

/**
 * Created by Administrator on 2016/5/23.
 */
public class Tea extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }


    @Override
    void addCondiments() {
        System.out.println("Adding Lemon");
    }
}
