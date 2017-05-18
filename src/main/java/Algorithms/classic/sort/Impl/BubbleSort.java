package Algorithms.classic.sort.Impl;

import Algorithms.classic.sort.ISort;

import java.util.Comparator;
import java.util.List;

public class BubbleSort<P, K> implements ISort<P, K>
{

    @Override
    public void sort(int[] arrays, Comparator<Integer> comparator)
    {
        for(int i = 0; i < arrays.length; i++)
        {
            for(int j = arrays.length - 1; j > i; j--)
            {
                if(comparator.compare(arrays[j - 1], arrays[j]) > 0)
                {
                    arrays[j - 1] = arrays[j - 1] ^ arrays[j];
                    arrays[j] = arrays[j - 1] ^ arrays[j];
                    arrays[j - 1] = arrays[j - 1] ^ arrays[j];
                }
            }
        }
    }
    
    @Override
    public void sort(List<P> list, String fieldName, Comparator<K> comparator)
    {
        
    }
}
