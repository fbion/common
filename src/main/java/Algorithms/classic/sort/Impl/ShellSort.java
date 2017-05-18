package Algorithms.classic.sort.Impl;

import Algorithms.classic.sort.ISort;

import java.util.Comparator;
import java.util.List;

public class ShellSort<P, K> implements ISort<P,K>
{

    @Override
    public void sort(int[] arrays, Comparator<Integer> comparator)
    {
        int i,j,d,t;
        d = arrays.length / 2;
        while(d > 0)
        {
            for(i = d; i < arrays.length; i++)
            {
                t = arrays[i];
                j = i - d;
                while((j >= 0 ) && (comparator.compare( arrays[j], t) > 0))
                {
                    arrays[j + d] = arrays[j];
                    j -= d;
                }
                arrays[j + d] = t;
            }
            d /= 2;
        }
    }

    @Override
    public void sort(List<P> list, String fieldName, Comparator<K> comparator)
    {
        
    }
}
