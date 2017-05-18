package Algorithms.classic;

/**
 * Created by Administrator on 2016/8/26.
 */
public class Chick {
    public static void main(String[] args) {
        boolean flag = false;
        int count = 0;
        outer:for (int g = 0; g <= 100; g++) {
            for (int x = 0; x <= 100; x += 3) {
                count++;
                int m = 100 - g - x;
                if(m < 0) {
                    break;
                }
                int price = x / 3 + m * 5 + g * 3;
                //不加此判断，循环1850次，加了循环1788次，但多判断了1788次，不知道效率哪个更高，
                // 大规模的循环，可以通过记录运行时间来看效率，小规模的运行时间太短，还没有浮动误差大，所以看不出来
                if(price < 100) {
                    break;
                }
                if(price == 100) {
                    System.out.println("G: " + g + "\tM: " + m + "\tX: " + x);
                    flag = true;
                    //开始以为是单解的
//                    break outer;
                }
            }
        }
        if(!flag) {
            System.out.println("No solution");
        }
        System.out.println("count : " + count);
    }
}
