package Algorithms.recursion;
public class MainRecursion {
    private static final int ZERO = 0;
    private static final int STEP = 1;
    private static final int END = 40;
    private static final int NUMBER_IN_LINE = 5;
    //判断一个整数是否为2的幂方并输出结果为true或false
    public static void main(String[] args) {
        if((args == null) || (args.length == 0))
        {
            main(new String[]{ZERO + ""});
        }
        else{
            int i = Integer.valueOf(args[0]);
            System.out.print(i + " : " + (i <= ZERO ? false : (i & (i - 1)) == 0) + "\t");
            if((i - ZERO + STEP)/STEP % NUMBER_IN_LINE == 0)
            {
                System.out.println();
            }
            i += STEP;
            if(i >= END)
            {
                return;
            }
            main(new String[]{i + ""});
        }
    }
}
