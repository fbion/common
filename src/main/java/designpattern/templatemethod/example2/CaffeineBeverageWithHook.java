package designpattern.templatemethod.example2;

/**
 * Created by Administrator on 2016/5/23.
 */
public abstract class CaffeineBeverageWithHook {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if(customerWantsCondiments()){
            addCondiments();
        }
    }

    public void boilWater() {
        System.out.println("Boiling water");
    }
    abstract void brew();
    public void pourInCup() {
        System.out.println("Pouring in cup");
    }
    abstract void addCondiments();

    public boolean customerWantsCondiments() {
        return true;
    }
}
