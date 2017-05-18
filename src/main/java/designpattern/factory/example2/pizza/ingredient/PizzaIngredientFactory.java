package designpattern.factory.example2.pizza.ingredient;

/**
 * Created by Administrator on 2016/5/9.
 */
public interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
    Veggies[] createVeggies();
    Pepperoni createPepperoni();
    Calms createCalm();
}
