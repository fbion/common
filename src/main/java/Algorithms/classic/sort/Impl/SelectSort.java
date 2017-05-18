package Algorithms.classic.sort.Impl;

import Algorithms.classic.sort.ISort;

import java.util.Comparator;
import java.util.List;

public class SelectSort<P, K> implements ISort<P, K>
{

    @Override
    public void sort(int[] arrays, Comparator<Integer> comparator)
    {
        int temp,pos;
        for(int i = 0; i < arrays.length; i++)
        {
            pos = i;
            temp = arrays[i];
            for(int j = i + 1; j < arrays.length; j++)
            {
                if(comparator.compare(temp, arrays[j]) > 0)
                {
                    pos = j;
                    temp = arrays[j];
                }
            }
            arrays[pos] = arrays[i];
            arrays[i] = temp;
            
        }
    }

    @Override
    public void sort(List<P> list, String fieldName, Comparator<K> comparator)
    {
        
    }
}
