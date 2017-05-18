package Algorithms.multithread.simple;

/**
 * Created by Administrator on 2016/3/9.
 */
public class PrintWithMultiTHread {
    static int total;
    static int totalNumber;
    static int count=0;

    static class MyThread implements Runnable
    {
        int id;
        public MyThread(int id){
            this.id=id;
        }
        @Override
        public void run() {
            while(count < totalNumber){
                if(count % total == id){
                    System.out.println(Thread.currentThread() + "\tcount=" + count);
                    count++;
                }
                else
                {
                    Thread.yield();
                }
            }
        }
    }

    public static void main(String[] args) {
        total = 10;
        totalNumber = 10000;
        for (int i = 0; i < total; i++) {
            new Thread(new MyThread(i)).start();
        }
    }
}
