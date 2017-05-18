package Algorithms;
public class Count1
{

    /*
     * 有一个整数n,写一个函数f(n),返回0到n之间出现的"1"的个数。 比如f(13)=6,现在f(1)=1,问下一个最大的f(n)=n的n是什么？
     * writed by chszs
     */
    public static int test(int n)
    {
        int sum = 0;
        for (int i = 1; i < n; i++)
        {
            sum += count2(i);
            if (sum == i)
            {
                System.out.println(i);
            }
        }
        return sum;
    }

    /**
     * 我自己的算法
     * 
     * @Title: count1
     * @Description: 我自己的算法
     * @param @param i
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    public static int count1(int i)
    {
        int result = 0;
        do
        {
            if (i % 10 == 1)
            {
                result++;
                // System.out.print(sum + " ");
            }
            i /= 10;
        } while (i != 0);
        return result;
    }

    /**
     * 别人的算法
     * 
     * @Title: count2
     * @Description: 别人的算法
     * @param @param i
     * @param @return 设定文件
     * @return int 返回类型
     * @throws
     */
    public static int count2(Integer i)
    {
        char[] re = i.toString().toCharArray();
        int le = re.length;
        int num = 0;
        for (int j = 0; j < le; j++)
        {
            if (re[j] == '1')
            {
                num++;
            }
        }
        return num;
    }

    public static void main(String[] args)
    {
        test(9999999);
    }
}
