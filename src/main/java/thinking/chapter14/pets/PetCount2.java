package thinking.chapter14.pets;

public class PetCount2
{

    public static void main(String[] args)
    {
//        PetCount.countPets(Pets.creator);
        PetCount.countPets(new LiteralPetCreator());
    }
}
