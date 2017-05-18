package work.qizhi.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/3/16.
 */
public class CalculateDouble {

    //套餐价格
    double[] prices = new double[]{50, 100};

    //行业库价格
    double repoPrice = 50;

    //行业库折扣
    double[] repoDiscount = new double[]{0.6, 0.5};

    List<Order> orderList = new ArrayList<>();

    class Order{
        int gradeId;
        int months;
        double moneyLeft;
        double gradePrice;
        double repoPrice;
        double totalFee;
        Date finishTime;
        int actualExcuteDays;
        double actualConsumedFees;
    }

    class SelectDisplay{

    }

    //购买时长优惠
    //满十月赠两月
    public static int Discount(int months){
        return months + months/10 * 2;
    }

    //模拟计算第一次购买
    public static void firstBuy(int gradeId, int months){}

    //模拟计算第二次购买
    public static void secondBuy(int gradeId, int months){}

    //模拟计算第三次购买
    public static void thirdBuy(int gradeId, int months){}

    public static void main(String[] args) {

    }
}
