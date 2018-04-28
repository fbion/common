package Algorithms.classic;

import java.util.Random;

/**
 * 项目名称：test<br/>
 * *********************************<br/>
 * <p>类名称：TestSort</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/6 16:10<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/6 16:10<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class TestSort {
    private static Random rand = new Random();

    public static int[] createRandomArray (int length, int max) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = rand.nextInt(max);
        }
        return result;
    }

    public static int[] insertSort(int[] array) {
        int temp, j;
        for (int i = 1; i < array.length; i++) {
            temp = array[i];
            j = i - 1;
            while(j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = temp;
        }
        return array;
    }

    public static int[] shellSort(int[] array) {
        int temp, j;
        int step = array.length / 2;
        while(step > 0) {
            for (int i = step; i < array.length; i++) {
                temp = array[i];
                j = i - step;
                while(j >= 0 && array[j] > temp) {
                    array[j + step] = array[j];
                    j -= step;
                }
                array[j + step] = temp;
            }
            step /= 2;
        }

        return array;
    }

    public static int[] bubbleSort(int[] array) {
        int temp;
        //外层从左边往右移
        for (int i = 0; i < array.length; i++) {
            //内层从右往左移，每次把最大或最小的，移到最左侧，这样外层当前序号前面的都是那次循环里最大或最小的
            for (int j = array.length - 1; j > 0; j--) {
                if(array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public static int[] selectSort(int[] array) {
        int temp, minIndex;
        for (int i = 0; i < array.length; i++) {
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if(array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public static int[] sort(int[] array) {
        //冒泡1
//        int temp;
//        for (int i = 0; i < array.length; i++) {
//            for (int j = array.length - 1; j > i; j--) {
//                if(array[j - 1] > array[j]) {
//                    temp = array[j];
//                    array[j] = array[j- 1];
//                    array[j - 1] = temp;
//                }
//            }
//        }

        //冒泡2
//        int temp;
//        for (int out = array.length - 1; out > 0; out--) {
//            for (int in = 0; in < out; in++) {
//                if(array[in] > array[in + 1]) {
//                    temp = array[in];
//                    array[in] = array[in + 1];
//                    array[in + 1] = temp;
//                }
//            }
//        }

        //选择排序
//        int tempIndex, temp;
//        for (int out = 0; out < array.length; out++) {
//            tempIndex = out;
//            for (int in = out + 1; in < array.length; in++) {
//                if(array[in] < array[tempIndex]) {
//                    tempIndex = in;
//                }
//            }
//            temp = array[out];
//            array[out] = array[tempIndex];
//            array[tempIndex] = temp;
//        }


        //插入排序
        int temp, current;
        for (int out = 1; out < array.length; out++) {
            current = array[out];
            for (int in = out - 1; in >= 0; in--) {
                if(array[in] < current) {
                    temp = array[in];
                    array[in] = array[in + 1];
                    array[in + 1] = temp;
                }
            }
        }







//        int temp, j;
//        for (int i = 0; i < array.length; i++) {
//            temp = array[i];
//            j = i - 1;
//            while (j >= 0 && array[j] > temp) {
//                array[j + 1] = array[j];
//                j--;
//            }
//            array[j + 1] = temp;
//        }


        //希尔
//        int temp, i, j;
//        int d = array.length / 2;
//        while (d > 0) {
//            for (i = d; i < array.length; i++) {
//                temp = array[i];
//                j = i - d;
//                while (j >= 0 && array[j] > temp) {
//                    array[j + d] = array[j];
//                    j -= d;
//                }
//                array[j + d] = temp;
//            }
//            d /= 2;
//        }

        //鸡尾酒排序
//        boolean flag = false;
//        int temp, tail, head, i, j;
//        tail = array.length - 1;
//        head = 0;
//        while(head < tail) {
//            j = tail;
//            for (i = head; i < j; i++) {
//                if(array[i] > array[i + 1]) {
//                    temp = array[i + 1];
//                    array[i + 1] = array[i];
//                    array[i] = temp;
//                    tail = i;
//                    flag = true;
//                }
//            }
//            j = head;
//            for (i = tail; i > j; i--) {
//                if(array[i - 1] > array[i]) {
//                    temp = array[i - 1];
//                    array[i - 1] = array[i];
//                    array[i] = temp;
//                    head = i;
//                }
//            }
//            if(flag) {
//                break;
//            }
//        }

//        int head, tail, temp, j;
//        boolean flag = true;
//        head = 0;
//        tail = array.length - 1;
//        while(flag)
//        {
//            j = tail;
//            flag = false;
//            for(int i = head; i < j; i++)
//            {
//                if(array[i] > array[i + 1])
//                {
//                    temp = array[i];
//                    array[i] = array[i + 1];
//                    array[i + 1] = temp;
//                    tail = i;
//                    flag = true;
//                }
//            }
//        }




        return array;
    }

    /**
     * <p>方法描述: 快排</p>
     *
     * <p>方法备注: </p>
     * @param   array   数组
     * @param   start   开始序号
     * @param   end     结束序号
     * @return  排序后的数组
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/27 16:30</p>
     */
    public static int[] quickSort(int[] array, int start, int end) {
        if(end <= start)
        {
            return array;
        }
        int i = start;
        int j = end;
        int flag = array[start];
        int temp;
        do
        {
            while(i < j && array[j] >= flag)
            {
                j--;
            }
            while(i < j && array[i] <= flag)
            {
                i++;
            }
            if(i < j)
            {
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }while(i < j);
        array[start] = array[j];
        array[j] = flag;
        quickSort(array, start, j - 1);
        quickSort(array, j + 1, end);
        return array;
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if(array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static void test() {
        int[] array;
        int[] tempArray;
        for (int i = 0; i < 10; i++) {
            array = createRandomArray(100000, 100000000);
            tempArray = array.clone();
            long start = System.currentTimeMillis();
            System.out.print(isSorted(sort(array)));
            System.out.print("\t\tduration1 : " + (System.currentTimeMillis() - start) + "\t\t\t\t");

            start = System.currentTimeMillis();
            System.out.print(isSorted(quickSort(tempArray, 0, array.length - 1)));
            System.out.println("\t\tduration2 : " + (System.currentTimeMillis() - start));
//            System.out.println(isSorted(quickSort(array, 0, array.length - 1)));
        }
    }

    public static void testPerformance() {
        int[] array;
        for (int i = 0; i < 10; i++) {
            //100_000, 100_000_000，快排9-22ms和希尔大概在16-46ms，直接插入在1300ms，冒泡排序在15-18s，
            array = createRandomArray(100_000, 100_000_000);
            long start = System.currentTimeMillis();
            System.out.print(isSorted(sort(array)));
            System.out.println("\t\tduration1 : " + (System.currentTimeMillis() - start) + "\t\t\t\t");
        }
    }

    public static int[] test(int[] unsorted) {
        int temp;
        for (int i = 0; i < unsorted.length; i++) {
            for (int j = i; j < unsorted.length; j++) {
                if(unsorted[i] > unsorted[j]) {
                    temp = unsorted[i];
                    unsorted[i] = unsorted[j];
                    unsorted[j] = temp;
                }
            }
        }
        return unsorted;
    }

    public static void main(String[] args) {
        int[] array;
        for (int i = 0; i < 10000; i++) {
            array = createRandomArray(10, 100);
            if(!isSorted(test(array))) {
                System.out.println(false);
                System.exit(1);
            }
        }
        System.out.println("over");

    }
}
