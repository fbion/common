package Algorithms.classic.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Test
{
    public static void sort(int[] arrays, Comparator<Integer> comparator)
    {
//        quickSort(arrays, 0, arrays.length, comparator);

//        int temp, step, j;
//        step = arrays.length / 2;
//        while(step > 0) {
//            for (int i = step; i < arrays.length; i++) {
//                temp = arrays[i];
//                for (j = i - step; comparator.compare(temp, arrays[j + step]) > 0 && j >= 0; j -= step) {
//                    arrays[j + step] = arrays[j];
//                }
//                arrays[j + step] = temp;
//            }
//            step /= 2;
//        }
        int temp;
        for (int i = 0; i < arrays.length; i++) {
            for (int j = arrays.length - 1; j > i; j--) {
                if(comparator.compare(arrays[j], arrays[j - 1]) > 0) {
                    temp = arrays[j];
                    arrays[j] = arrays[j - 1];
                    arrays[j - 1] = temp;
                }
            }
        }
    }

    public static void sort(int[] array)
    {
        //BubbleSort
//        int temp;
//        for (int i = 0; i < array.length; i++) {
//            for (int j = array.length - 1; j > i; j--) {
//                if(array[j] > array[j - 1]) {
//                    temp = array[j];
//                    array[j] = array[j - 1];
//                    array[j - 1] = temp;
//                }
//            }
//        }

        //InsertSort
//        int temp;
//        for (int i = 1; i < array.length; i++) {
//            for (int j = i; j > 0; j--) {
//                if(array[j] > array[j - 1]) {
//                    temp = array[j];
//                    array[j] = array[j - 1];
//                    array[j - 1] = temp;
//                }
//            }
//        }

        //Select Sort
//        int temp,tempIndex;
//        for (int i = 0; i < array.length; i++) {
//            tempIndex = i;
//            for (int j = i + 1; j < array.length; j++) {
//                if(array[j] > array[tempIndex]) {
//                    tempIndex = j;
//                }
//            }
//            temp = array[tempIndex];
//            array[tempIndex] = array[i];
//            array[i] = temp;
//        }
    }

    public static void quickSort(int[] arrays, int start, int end, Comparator<Integer> comparator)
    {
        if(end  <= start)
        {
            return;
        }
        int i = start;
        int flag = arrays[start];
        int j = end - 1;
        do
        {
            while(i < j && comparator.compare(arrays[j], flag) >= 0)
            {
                j--;
            }
            arrays[i] = arrays[j];
            while(i < j && comparator.compare(arrays[i], flag) <= 0)
            {
               i++;
            }
            arrays[j] = arrays[i];
        }while(i < j);
        arrays[i] = flag;
        quickSort(arrays, start, j - 1, comparator);
        quickSort(arrays, j + 1, end, comparator);
    }

    private static int[] createRandomArrays(int size)
    {
        Random rand = new Random();
        int[] result = new int[size];
        for (int i = 0; i < size; i++)
        {
            result[i] = rand.nextInt(100);
        }
        return result;
    }

    public static void main(String[] args) throws NoSuchFieldException, SecurityException
    {
        int[] arrays = createRandomArrays(9);
        System.out.println(Arrays.toString(arrays));

//        new QuickSort().sort(arrays,new Comparator<Integer>()

//        sort(arrays, new Comparator<Integer>()
//        {
//            @Override
//            public int compare(Integer o1, Integer o2)
//            {
//                return o1 - o2;
//            }
//        });

        sort(arrays);
        System.out.println(Arrays.toString(arrays));
    }
}