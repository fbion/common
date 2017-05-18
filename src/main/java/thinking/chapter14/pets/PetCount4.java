package thinking.chapter14.pets;

import thinking.chapter14.utils.TypeCounter;

public class PetCount4
{

    public static void main(String[] args)
    {
        TypeCounter counter = new TypeCounter(Pet.class);
        for (Pet pet : Pets.createArray(20))
        {
            System.out.print(pet.getClass().getSimpleName() + " ");
            counter.count(pet);
        }
        System.out.println();
        System.out.println(counter);
    }
}
