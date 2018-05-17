package ebook.thinking.chapter18.serialize.test;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/24.
 */
public class Test implements Serializable {

    String name;

    private void writeObject(ObjectOutputStream stream)
    {
        System.out.println("In writeObject.");
    }

    private void readObject(ObjectInputStream stream)
    {
        System.out.println("In readObject.");
        name="zhangsan";
    }

    public String toString()
    {
        return name;
    }

    static final double PRICESION = 0.0001;

    public static void main(String[] args) {

        double amount = 36;

        double repoDiscount = 0;
        double repoPrice = 0;
        String repoName = null;
        double repoFee = repoDiscount * repoPrice;
        double leastMonths = 0;
        double newGradePrice = 50;
        double moneyLeft = 0;
        double leastDays = 0;
        double leftDays = 0;
        double totalFeeDouble = 1500;
        System.out.println("repoFee : " + repoFee);
        System.out.println("amount : " + amount);
        System.out.println("leastMonths : " + leastMonths);
        System.out.println("(amount - (int) (amount / 10 * 2) = " + (amount - ((int) (amount / 10)) * 2));
        double newOrderMoney = newGradePrice
                * (amount - (int) (amount / 10 * 2)) + repoFee;

        // gradeFee = totalMoney - repoFee - moneyLeft;
        System.out.println("moneyLeft : " + moneyLeft);
        System.out.println("newOrderMoney : " + newOrderMoney);
        System.out.println("newGradePrice : " + newGradePrice);
        System.out.println("leastDays : " + leastDays);
        System.out.println("leftDays : " + leftDays);
//        System.out.println("totalFee : " + Double.parseDouble(totalFee));
        System.out
                .println("pricesion1 : "
                        + (newOrderMoney + newGradePrice
                        * (leastDays - leftDays) / 30 - totalFeeDouble));
        System.out.println("pricesion2 : "
                + (totalFeeDouble - (newOrderMoney + newGradePrice
                * (leastDays - leftDays) / 30)));
        if (newOrderMoney + newGradePrice * (leastDays - leftDays) / 30
                - totalFeeDouble > PRICESION
                || totalFeeDouble
                - (newOrderMoney + newGradePrice
                * (leastDays - leftDays) / 30) > PRICESION)
        {
            System.out.println("Error!交易金额不合法！");
        }

        for (int i = 0; i < 37; i++) {
            double num = i;
            System.out.print((num - (int) (num / 10 * 2)));
            System.out.print("\t\t");
            System.out.print((num - (int) (num / 10) * 2));
            System.out.println();
        }
    }
}
