package designpattern.composite.example1;

/**
 * Created by Administrator on 2016/5/24.
 * HeadFirst设计模式中的组合模式例子
 */
public class MenuTestDrive {
    public static void main(String[] args) {
        MenuComponent pancakeHouseMenu = new Menu("PANCAKE HOUSE MANU", "Breakfast");
        MenuComponent dinnerMenu = new Menu("DINNER MANU", "Lunch");
        MenuComponent cafeMenu = new Menu("CAFE MANU", "Dinner");
        MenuComponent dessertMenu = new Menu("DESSERT MANU", "Dessert of course");

        MenuComponent allMenus = new Menu("ALL MENUS", "All menus combined");
        allMenus.add(pancakeHouseMenu);
        allMenus.add(dinnerMenu);
        allMenus.add(cafeMenu);

        dinnerMenu.add(new MenuItem("Pasta", "Spaghtti with Marinara Sauce, and a slice of sourdough bread", true, 3.89));
        MenuComponent menu1 = new Menu("1", "1");
        menu1.add(new MenuItem("11", "11", true, 3.12));
        menu1.add(new MenuItem("12", "12", true, 3.32));

        MenuComponent menu2 = new Menu("2", "2");
        menu2.add(new MenuItem("21", "21", true, 3.42));
        menu2.add(new MenuItem("22", "22", true, 3.62));

        dinnerMenu.add(menu1);
        dinnerMenu.add(menu2);
        allMenus.add(dessertMenu);
        dessertMenu.add(new MenuItem("Apple Pie", "Apple Pie    with a flakey crust, topped with vanille ice cream" ,true, 1.59));

        Waitress waitress = new Waitress(allMenus);

        waitress.printMenu();
    }
}
