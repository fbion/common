package ebook.thinking.chapter14.pets;

import java.util.LinkedHashMap;
import java.util.Map;

public class PetCount3
{
    @SuppressWarnings("serial")
    static class PetCounter extends
            LinkedHashMap<Class<? extends Pet>, Integer>
    {
        private static Map<Class<? extends Pet>,Integer> createMap(Iterable<Class<? extends Pet>> iterable, int i){
            Map<Class<? extends Pet>,Integer> map = new LinkedHashMap<Class<? extends Pet>,Integer>();
            for (Class<? extends Pet> clazz : iterable)
            {
                map.put(clazz, i);
            }
            return map;
        }
        public PetCounter()
        {
            super(createMap(LiteralPetCreator.allTypes, 0));
        }

        public void count(Pet pet)
        {
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet())
            {
                if(pair.getKey().isInstance(pet))
                    put(pair.getKey(), pair.getValue() + 1);
            }
        }

        public String toString() {
            StringBuffer result = new StringBuffer("{");
            for (Map.Entry<Class<? extends Pet>, Integer> pair : entrySet())
            {
                result.append(pair.getKey().getSimpleName());
                result.append("=");
                result.append(pair.getValue());
                result.append(". ");
            }
            result.delete(result.length()-2, result.length());
            result.append("}");
            return result.toString();
        }
    }
    public static void main(String[] args)
    {
        PetCounter petCount = new PetCounter();
        for (Pet pet : Pets.createArray(20))
        {
            System.out.print(pet.getClass().getSimpleName() + " ");
            petCount.count(pet);
        }
        System.out.println();
        System.out.println(petCount);
    }
}
