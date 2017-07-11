package Algorithms;

import java.util.ArrayList;
import java.util.LinkedList;

public class KickMInN
{

    /**
     * Josephus problem
     * 约瑟夫问题
     * n个人围一圈循环报数，报到m的kick出列，剩下的人继承，算出最后剩下的一个人
     * @param n
     * @param m
     * @return
     */
    public static int kickMinN(int n, int m)
    {
        LinkedList<Integer> list = new LinkedList<Integer>();
        //把N个人按编号存入队列
        for (int i = 0; i < n; i++)
        {
            list.add(i+1);
        }
        int temp = 0;
        while (list.size() > 1)
        {
            //temp表示当前报数的编号，因为当前的人要报1，所以再加m - 1得到报m号的人的编号，
            // 因为是循环报数，所以加完后可能比队列剩余人数大，要对剩余人数取下余，
            // 当然也可以不直接取余，而是判断一下是不是比剩余人数大，是的话，再取余，或减去当前剩余人数也可，这样要多写判断代码
            temp += m -1;
            temp %= list.size();
            System.out.print(list.remove(temp) + "\t");
        }
        return list.get(0);
    }

    /**
     * n个人围一圈循环报数，报到m的kick出列，剩下的人继承，算出最后剩下的一个人
     * @param n
     * @param m
     * @return
     */
    public static int kickMinNByArray(int n, int m)
    {
        //int型数组初始化为0，所以以0表示当前编号的人没有出列
        int[] array = new int[n];
        //当前人的编号
        int index = 0;
        //计数表示当前人报的数
        int count = 0;
        int left = n;
        while(left > 1) {
            //判断当前编号的人是否出列，没出列，则报的数加1
            if(array[index] == 0) {
                count++;
                if(count == m ) {
                    array[index] = 1;
                    System.out.print((index + 1) + "\t");
                    left--;
                    count = 0;
                }
            }
            //index指向下一个人，对总人数取余，也可加判断，超过总人数再取余
            index ++;
            index %= n;
        }
        //遍历数组找到剩余的一个人的编号并返回
        for (int i = 0; i < n; i++) {
            if(array[i] == 0) {
                return i + 1;
            }
        }
        return -1;
    }

    public static void test() {
        ArrayList<String> al = new ArrayList<String>();
        al.add("person1");
        al.add("person2");
        al.add("person3");
        al.add("person4");
        al.add("person5");
        al.add("person6");

        int index = -1;
        int count = 0;
        boolean needAdd = true;

        for (; count <= 36; )//数数数到36足够找到最后一个人了
        {
            count++; //数数
            if (needAdd) {
                index++; //数数的同时，调整当前数数对应的索引号
                if (index > al.size() - 1) {
                    index = 0;
                }
            } else {
                needAdd = true;
            }


            if (count == 5) {
                if (al.size() == 1) {
                    System.out.println("OKKKK!  find last persion" + index + ", name=" + al.get(index));
                    break;
                }

                System.out.println("remove index=" + index + ", name=" + al.get(index));
                //移除后，下次不再加1，因为arrayList移除后后面如果有有会补充到当前移除的index位置上，就相当于已经index++了
                if (index < al.size() - 1)
                    needAdd = false;
                al.remove(index);//移除当前index的人物
            } else if (count == 6) {
                count = 0;//从头开始数数
            }
        }
    }

    public static void main(String[] args)
    {
        System.out.println(kickMinN(30, 9));
        System.out.println(kickMinNByArray(30, 9));
//        test();
    }
}
