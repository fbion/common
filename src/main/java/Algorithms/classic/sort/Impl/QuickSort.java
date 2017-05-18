package Algorithms.classic.sort.Impl;

import Algorithms.classic.sort.ISort;

import java.util.Comparator;
import java.util.List;

public class QuickSort<P, K> implements ISort<P, K>
{

    @Override
    public void sort(int[] arrays, Comparator<Integer> comparator)
    {
        quickSort(arrays, 0, arrays.length - 1, comparator);
    }
    
    private void quickSort(int[] arrays, int start, int end, Comparator<Integer> comparator)
    {
        if(end <= start)
        {
            return;
        }
        int i = start;
        int j = end;
        int flag = arrays[start];
        int temp;
        do
        {
            while(i < j && comparator.compare(arrays[j], flag) >= 0)
            {
                j--;
            }
            while(i < j && comparator.compare(arrays[i], flag) <= 0)
            {
                i++;
            }
            if(i < j)
            {
                temp = arrays[i];
                arrays[i] = arrays[j];
                arrays[j] = temp;
            }
        }while(i < j);
        arrays[start] = arrays[j];
        arrays[j] = flag;
        quickSort(arrays, start, j - 1, comparator);
        quickSort(arrays, j + 1, end, comparator);
    }
    
    @Override
    public void sort(List<P> list, String fieldName, Comparator<K> comparator)
    {
        
    }
}
