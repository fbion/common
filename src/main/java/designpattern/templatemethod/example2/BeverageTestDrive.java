package designpattern.templatemethod.example2;

/**
 * Created by Administrator on 2016/5/23.
 */
public class BeverageTestDrive {
    public static void main(String[] args) {
        TeaWithHook teaHook = new TeaWithHook();
        CoffeeWithHook coffeeHook = new CoffeeWithHook();

        System.out.println("\nMaking Tea");
        teaHook.prepareRecipe();

        System.out.println("\nMaking Coffee");
        coffeeHook.prepareRecipe();
    }
}
