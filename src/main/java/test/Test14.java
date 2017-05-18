package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/28.
 */
public class Test14 {
    public static void main(String[] args){
        Multiple m =new Multiple();
        System.out.println(m.NumberOff(18,6));
    }

}
class Multiple
{
    public int NumberOff(int x,int y){
        int t;
        if (x<y)
        {
            t=x;
            x=y;
            y=t;
        }
        while (y!=0)
        {
            if(x==y)
                return x;
            else{
                int k = x % y;
                x=y;
                y=k;
            }
        }
        return x;
    }
    public int NumberOff1(int val1, int val2) {
        if(val1 == 0 || val2 ==0) {
            System.exit(1);
        }
        int temp;
        while((temp = val1 % val2) != 0) {
            val1 = val2;
            val2 = temp;
        }
        return Math.abs(val1);
    }
}


