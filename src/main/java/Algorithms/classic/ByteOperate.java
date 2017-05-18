package Algorithms.classic;

public class ByteOperate
{

    public static boolean isOdd(int number)
    {
        return (number & 1) == 1;
    }
    
    public static int divide2(int number, int n)
    {
        return number>>n;
    }
    
    public static int multiply2(int number, int n)
    {
        return number<<n;
    }
    
    public static void main(String[] args)
    {
        System.out.println(isOdd(4));
    }
}
