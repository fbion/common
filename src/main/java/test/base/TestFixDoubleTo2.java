package test.base;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Administrator on 2016/3/22.
 */
public class TestFixDoubleTo2 {
    public static void main(String[] args) {
        double   f   =   12.445;
        BigDecimal b   =   new   BigDecimal(f);
        double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);

        DecimalFormat   df   =new DecimalFormat("#.00");
        System.out.println(df.format(f));

        String d = "3.1415926";
        String result = String.format("%.2f", d);
        System.out.println(result);
    }
}
