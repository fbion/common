package Algorithms.classic.sort.Impl;

import Algorithms.classic.sort.ISort;

import java.util.Comparator;
import java.util.List;

public class GoodBubbleSort<P, K> implements ISort<P, K>
{
    
    @Override
    public void sort(int[] arrays, Comparator<Integer> comparator)
    {
        int head, tail, temp, j;
        boolean flag = true;
        head = 0;
        tail = arrays.length - 1;
        while(flag)
        {
            j = tail;
            flag = false;
            for(int i = head; i < j; i++)
            {
                if(comparator.compare(arrays[i], arrays[i + 1]) > 0)
                {
                    temp = arrays[i];
                    arrays[i] = arrays[i + 1];
                    arrays[i + 1] = temp;
                    tail = i;
                    flag = true;
                }
            }
        }
    }
    
    public void sort2(int[] arrays, Comparator<Integer> comparator)
    {
        int head, tail, temp, j;
        boolean flag = true;
        head = 0;
        tail = arrays.length - 1;
        while(flag)
        {
            j = tail;
            flag = false;
            for(int i = head; i < j; i++)
            {
                if(comparator.compare(arrays[i], arrays[i + 1]) > 0)
                {
                    temp = arrays[i];
                    arrays[i] = arrays[i + 1];
                    arrays[i + 1] = temp;
                    tail = i;
                    flag = true;
                }
            }
            if(!flag)
            {
                break;
            }
            j = head;
            for(int i = tail; i > j; i--)
            {
                if(comparator.compare(arrays[i - 1], arrays[i]) > 0)
                {
                    temp = arrays[i];
                    arrays[i] = arrays[i - 1];
                    arrays[i - 1] = temp;
                    head = i;
                    flag = true;
                }
            }
        }
    }
    
    @Override
    public void sort(List<P> list, String fieldName, Comparator<K> comparator)
    {
        
    }
}
