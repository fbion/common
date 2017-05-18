package designpattern.iterator.example1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/24.
 */
public class MenuTestDrive {
    public static void main(String[] args) {
        Menu pancakeHouseMenu = new PancakeHouseMenu();
        Menu dinerMenu = new DinerMenu();

        List<Menu> list = new ArrayList<>();
        list.add(pancakeHouseMenu);
        list.add(dinerMenu);
        Waitress waitress = new Waitress(list);

        waitress.printMenu();
    }
}
