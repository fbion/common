package Algorithms.multithread.simple;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

public class FibonacciWithMultiThread
{

    private static int i = 1;
    private static int j = 1;
    private static int turn = 1;
    
    public static void print(int id)
    {
        if(turn%2 == 1)
        {
            System.out.println("turn=" + turn + "\t i=" + i + "\t" + id);
        }
        else
        {
            System.out.println("turn=" + turn + "\t j=" + j + "\t" + id);
        }
    }
    
    public static void add()
    {
        if(turn%2 == 1)
        {
            i += j;
        }
        else
        {
            j += i;
        }
    }
    
    static class FibonacciPrint implements Runnable 
    {
        private int t;
        private int id;
        public FibonacciPrint(int id, int t)
        {
            this.id = id;
            this.t = t;
        }
        @Override
        public void run()
        {
            while(i < 1000000)
            {
                if(turn%3 == t)
                {
                    print(id);
                    add();
                    turn++;
                }
                Thread.yield();
            }
            if(turn%3 == id)
            {
                System.out.println("i=" + i + "\t j=" + j);
            }
        }
    }
    
    
    public static void main(String[] args)
    {
        ThreadPool threadPool = null;
        new Thread(new FibonacciPrint(1,1)).start();
        new Thread(new FibonacciPrint(2,2)).start();
        new Thread(new FibonacciPrint(3,0)).start();
    }
    
}
