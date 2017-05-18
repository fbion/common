package Algorithms.puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/24.
 */
public class InsertIntoArray {
    /**
     * 面试题
     * 给出一个从小到大排好序的整数数组nums和一个整数n，在数组中添加若干个补丁（元素）使得[1,n]的区间内的所有数都可以表示成nums中若干个数的和。
     * 返回最少需要添加的补丁个数。
     * Example 1：
     * nums = [1, 3], n = 6
     * 返回1，表示至少需要添加1个数｛2｝，才可以表示1到6之间所有数。
     * Example 2:
     * nums = [1, 5, 10], n = 20
     * 返回2，表示至少需要添加两个数｛2，4｝，才可以表示1到20之间所有数。
     * 求 nums = [1, 2, 8 , 13 ,19 ,109 ], n = 2048 补丁数组  restult=[ ] ?
     * @param array
     * @param n
     * @return
     */
    public static int inserIntoArray(int[] array, int n) {

        return 0;
    }

    public static int[] findSuppleInt(int[] array, int i) {
        for (int j = 0; j < array.length; j++) {

        }
        return array;
    }

    /**
     * 在给定数组里求出和为给定值sum的int序列并返回
     * @param array
     * @param sum
     * @return
     */
    public static List<Integer> findList(int[] array, int sum) {
        List<Integer> list = new ArrayList<>();
        List<Integer> indexList = new ArrayList<>();
        //保存剩余总和
        int tempSum = sum;
        //保存数组开始遍历的序号
        int index = array.length - 1;
        do {
            for (int i = index; i >= 0 && tempSum > 0; i--) {
                if(array[i] > tempSum) {
                    continue;
                }
                //小于剩余总和，就加入结果集，并把序号-1用于回溯遍历，保存到序号列表，更新剩余总和
                list.add(array[i]);
                tempSum -= array[i];
                index = i - 1;
                indexList.add(index);
            }
            //tempSum为0，则表示已经找到符合要求的list
            if(tempSum ==0) {
                return list;
            }
            //遍历完数组，没找到符合要求的list,则回溯到上一个保存的index处重新遍历
            if(list.size() > 0) {
                tempSum += list.remove(list.size() - 1);
                index = indexList.remove(indexList.size() - 1);
            }
        } while(list.size() != 0);
        return null;
    }

    public static void main(String[] args) {
//        inserIntoArray(new int[]{1, 3}, 6);
        System.out.println(findList(new int[]{2,4,5,6}, 12));
        System.out.println(findList(new int[]{1,4,5,7,8,9,10,11,12}, 35));
    }


}
