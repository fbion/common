package Algorithms.classic.sort;

import java.util.Comparator;
import java.util.List;

public interface ISort<P,K>
{
    void sort(int[] arrays, Comparator<Integer> comparator);
    void sort(List<P> list, String fieldName, Comparator<K> comparator);
}
