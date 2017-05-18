package Algorithms.classic;

public class Swap
{

    /**
     * 利用位运算异或实现两个数交互换
     * 
     * @Title: swap
     * @Description: 利用位运算异或实现两个数交互换
     * @param @param arrays
     * @param @return 设定文件
     * @return int[] 返回类型
     * @throws
     */
    public static int[] swap(int[] arrays)
    {
        arrays[0] = arrays[0] ^ arrays[1];
        arrays[1] = arrays[0] ^ arrays[1];
        arrays[0] = arrays[0] ^ arrays[1];
        return arrays;
    }

    /**
     * 利用位运算异或实现两个数交互换
     *
     * @Title: swap
     * @Description: 利用位运算异或实现两个数交互换
     * @param @param arrays
     * @param @return 设定文件
     * @return int[] 返回类型
     * @throws
     */
    public static int[] swap1(int[] arrays)
    {
        arrays[0] = (arrays[1] ^= ( arrays[0] ^= arrays[1])) ^ arrays[0];
        return arrays;
    }

    /**
     * 利用加减实现两个数交互换,局限于两个数的中间运算结果不能溢出
     * 
     * @Title: swap2
     * @Description: 利用加减实现两个数交互换,局限于两个数的中间运算结果不能溢出
     * @param @param arrays
     * @param @return 设定文件
     * @return int[] 返回类型
     * @throws
     */
    public static int[] swap2(int[] arrays)
    {
        arrays[0] = arrays[0] + arrays[1];
        arrays[1] = arrays[0] - arrays[1];
        arrays[0] = arrays[0] - arrays[1];
        return arrays;
    }

    /**
     * 利用加减实现两个数交互换,局限于两个数的中间运算结果不能溢出
     *
     * @Title: swap2
     * @Description: 利用加减实现两个数交互换,局限于两个数的中间运算结果不能溢出
     * @param @param arrays
     * @param @return 设定文件
     * @return int[] 返回类型
     * @throws
     */
    public static int[] swap3(int[] arrays)
    {
        arrays[0] = arrays[0] - arrays[1];
        arrays[1] = arrays[1] + arrays[0];
        arrays[0] = arrays[1] - arrays[0];
        return arrays;
    }

    public static void main(String[] args)
    {
//        int[] arrays = new int[] { 1, 2 };
//        System.out.println(Arrays.toString(swap1(arrays)));
//
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                int a = i;
//                int b = j;
//                System.out.print("before: " + a + "\t");
//                System.out.print(b + "\t");
//                a = (b ^= (a ^= b))^a;
//                System.out.print("after: " + a + "\t");
//                System.out.println(b);
//            }
//        }

        int a = 1;
        int b = 2;
        int n = 100000000;

        long start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int m = a;
            a = b;
            b  = m;
        }
        System.out.println(System.nanoTime() - start);

        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            a = a + b;
            b = a - b;
            a = a - b;
        }
        System.out.println(System.nanoTime() - start);

        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
//            a = (b ^= (a ^= b)) ^ a;
        }
        System.out.println(System.nanoTime() - start);
    }
}
