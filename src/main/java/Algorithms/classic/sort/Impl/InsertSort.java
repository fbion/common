package Algorithms.classic.sort.Impl;

import Algorithms.classic.sort.ISort;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

public class InsertSort<P, K> implements ISort<P, K>
{
    private Type P;
    @Override
    public void sort(int[] arrays, Comparator<Integer> comparator)
    {
        int temp,j;
        for(int i = 0; i < arrays.length; i++)
        {
            temp = arrays[i];
            for(j = i; j > 0 && comparator.compare(arrays[j - 1] , temp) > 0; j--)
            {
                arrays[j] = arrays[j - 1];
            }
            arrays[j] = temp;
        }
    }
    
    @Override
    public void sort(List<P> list, String fieldName, Comparator<K> comparator)
    {
        Field field = null;
        P temp;
        K tempField;
        int j;
        try
        {
            field = P.getClass().getField(fieldName);
            for(int i = 0; i < list.size(); i++)
            {
                temp = list.get(i);
                tempField = (K)field.get(list.get(i));
                for(j = i - 1; j > 0; j--)
                {
                    if(comparator.compare((K)field.get(list.get(j - 1)), tempField) > 0)
                    {
                        list.set(j, list.get(j - 1));
                    }
                }
                list.set(j, temp);
            }
        }
        catch(NoSuchFieldException | SecurityException e)
        {
            e.printStackTrace();
        }
        catch(IllegalArgumentException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }
}
