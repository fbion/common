package Algorithms.classic;

public class TestPowerOfTwo
{
    /**
     * 位运算检查一个数是否为2的幂方算法1
     * @param val
     * @return
     */
    public static boolean isPowerOfTwoSimple(int val){
        return val != 0 && ((val & -val) == val);
    }

    /**
     * 位运算检查一个数是否为2的幂方算法2
     * @param val
     * @return
     */
    public static boolean isPowerOf2(int val){
        return val != 0 && (val & (val - 1))==0;
    }

    /**
     * 递归检查一个数是否为2的幂方
     * @param val
     * @return
     */
    public static boolean isPowerOfTwoRecurse(int val){
        if(val == 0)
        {
            return false;
        }
        if(val == 1)
        {
            return true;
        }
        if(val % 2 == 0)
        {
            return isPowerOfTwoRecurse(val/2);
        }
        else
        {
            return false;
        }
    }

    /**
     * 循环检查一个数是否为2的幂方
     * @param val
     * @return
     */
    public static boolean isPowerOfTwoLoop(int val){
        if(val == 0)
        {
            return false;
        }
        while(val%2 ==0)
        {
            val /= 2;
        }
        if(val == 1)
        {
            return true;
        }
        return false;
    }

    public static void print(boolean flag){
        if(flag) {
            System.out.print(true + "----");
        }

    }
    
    public static void main(String[] args)
    {
        for(int i = -128; i < 129; i++)
        {
            System.out.print(i + "----");
            print(isPowerOfTwoSimple(i));
            print(isPowerOf2(i));
            print(isPowerOfTwoRecurse(i));
            print(isPowerOfTwoLoop(i));
            System.out.println();
        }
    }
}