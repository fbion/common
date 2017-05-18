package thinking.chapter14.pets;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator
{
    private static List<Class<? extends Pet>> types = new ArrayList<Class<? extends Pet>>();

    private static String[] typeNames = { "thinking.chapter14.pets.Mutt",
            "thinking.chapter14.pets.Pug",
            "thinking.chapter14.pets.EgyptianMau",
            "thinking.chapter14.pets.Manx", "thinking.chapter14.pets.Cymric",
            "thinking.chapter14.pets.Rat", "thinking.chapter14.pets.Mouse",
            "thinking.chapter14.pets.Hamster", };

    @SuppressWarnings("unchecked")
    private static void loader()
    {
        try
        {
            for (String str : typeNames)
            {
                types.add((Class<? extends Pet>) Class.forName(str));
            }
        }
        catch(ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
    }

    static
    {
        loader();
    }

    @Override
    public List<Class<? extends Pet>> types()
    {
        return types;
    }
}
