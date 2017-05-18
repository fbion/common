package test.recent;

/**
 * Created by Administrator on 2016/1/13.
 */
public class CalculateCombination {
    public static void main(String[] args) {
        //计算total为21的组合可能性
        long result = 1;
        int total = 21;
        for (int i = 1; i < total; i++) {
            long temp1 = 1;
            long temp2 = 1;
            //total总数取i个的排列数
            for (int j = total; j > total - i; j--) {
                temp1 *= j;
            }
            //i的阶层
            for (int k = 1; k <= i; k++) {
                temp2 *= k;
            }
            System.out.print("i = " + i + ":");
            long temp = temp1 / temp2;
            System.out.println("temp1 = " + temp1 + "\t temp2 = " + temp2 + "\t result += " + temp);
            result += temp;
        }
        System.out.println(result);
    }
}
