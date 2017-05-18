package Algorithms.multithread.simple;

import java.util.ArrayList;
import java.util.List;

public class YangHuiWithMultiThread
{
    private String separate = "\t";
    
    private int total;
    
    private int lines;
    
    private boolean turn = true;
    
    public YangHuiWithMultiThread(int lines) {
        this.total = lines;
        this.lines = lines;
    }
    
    public YangHuiWithMultiThread(int lines, String separate) {
        this.total = lines;
        this.lines = lines;
        this.separate = separate;
    }
    
    public void printYangHui() {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(lines > 0) {
                    while(turn) {
                        int count = lines - 1;
                        while(count-- > 0) {
                            System.out.print(separate);
                        }
                        turn = false;
                        Thread.yield();
                        break;
                    }
                }
            }
        }).start();
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                List<Integer> list1 = new ArrayList<Integer>();
                list1.add(1);
                List<Integer> list2 = new ArrayList<Integer>();
                list2.add(1);
                list2.add(1);
                while(lines > 0 ) {
                    while(!turn) {
                        int count = total - lines;
                        switch(count) {
                            case 0:
                                System.out.print(list1.get(0));
                                break;
                            case 1:
                                System.out.print(list2.get(0) + separate + separate + list2.get(1));
                                break;
                            default:
                                List<Integer> temp1,temp2;
                                if(count%2 == 0) {
                                    temp1 = list1;
                                    temp2 = list2;
                                }else {
                                    temp1 = list2;
                                    temp2 = list1;
                                }
                                temp1.removeAll(temp1);
                                temp1.add(1);
                                System.out.print(1 + separate + separate);
                                for(int i = 1; i < temp2.size(); i++) {
                                    temp1.add(temp2.get(i - 1) + temp2.get(i));
                                    System.out.print(temp1.get(i) + separate + separate);
                                }
                                temp1.add(1);
                                System.out.print(1);
                        }
                        System.out.println();
                        lines--;
                        turn = true;
                        Thread.yield();
                        break;
                    }
                }
            }
        }).start();
    }
    
    public static void main(String[] args)
    {
        new YangHuiWithMultiThread(10, "  ").printYangHui();
    }
}
