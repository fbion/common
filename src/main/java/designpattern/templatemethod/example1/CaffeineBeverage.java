package designpattern.templatemethod.example1;

/**
 * Created by Administrator on 2016/5/23.
 */
public abstract class CaffeineBeverage {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }


    public void boilWater() {
        System.out.println("Boiling water");
    }


    abstract void brew();


    public void pourInCup() {
        System.out.println("Pouring in cup");
    }


    abstract void addCondiments();
}
