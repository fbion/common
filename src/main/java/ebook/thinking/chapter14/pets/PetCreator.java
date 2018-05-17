package ebook.thinking.chapter14.pets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class PetCreator
{
    private Random rand = new Random(47);

    /**
     * 模板方法设计模式的变体
     *
     * @Title: types
     * @Description: 模板方法设计模式的变体
     * @param @return 设定文件
     * @return List<Class<? extends Pet>> 返回类型
     * @throws
     */
    public abstract List<Class<? extends Pet>> types();

    /**
     * create one pet
     *
     * @Title: randomPet
     * @Description: create one pet
     * @param @return 设定文件
     * @return Pet 返回类型
     * @throws
     */
    public Pet randomPet()
    {
        int n = rand.nextInt(types().size());
        try
        {
            return types().get(n).newInstance();
        }
        catch(InstantiationException e)
        {
            throw new RuntimeException(e);
        }
        catch(IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * create an array of pets
     *
     * @Title: createArray
     * @Description: reate an array of pets
     * @param @param size
     * @param @return 设定文件
     * @return Pet[] 返回类型
     * @throws
     */
    public Pet[] createArray(int size)
    {
        Pet[] result = new Pet[size];
        for (int i = 0; i < size; i++)
        {
            result[i] = randomPet();
        }
        return result;
    }

    /**
     * create a list of pets
     *
     * @Title: arrayList
     * @Description: create a list of pets
     * @param @param size
     * @param @return 设定文件
     * @return List<Pet> 返回类型
     * @throws
     */
    public ArrayList<Pet> arrayList(int size)
    {
        ArrayList<Pet> result = new ArrayList<Pet>();
        Collections.addAll(result, createArray(size));
        return result;
    }
}
