package ebook.thinking.chapter15;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2016/3/4.
 */
public class CheckedList {
    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List probablyDogs){
        probablyDogs.add(new Cat());
    }

    public static void main(String[] args) {
        List<Dog> dogs1 = new ArrayList<>();
        oldStyleMethod(dogs1);
        List<Dog> dogs2 = Collections.checkedList(new ArrayList<Dog>(), Dog.class);
        try
        {
            oldStyleMethod(dogs2);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        List<Pet> pets = Collections.checkedList(new ArrayList<Pet>(), Pet.class);
        pets.add(new Dog());
        pets.add(new Cat());
    }
}
