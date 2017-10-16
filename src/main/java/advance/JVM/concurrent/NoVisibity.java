package advance.JVM.concurrent;

/**
 * description： Java并发编程实战中给的一个简单的例子，说是可能输出0或无限循环，我循环了100万次，也没输出0或无限循环<br>
 * createTime: 2017/10/1117:08 <br>
 *
 * @author zzh
 */
public class NoVisibity extends Thread {

    private boolean ready;

    private int number;

    private class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
                System.out.println("ReaderThread yield");
            }
            if(number == 0) {
                System.out.print("\t" + number + "\t");
            }
        }
    }


    @Override
    public void run() {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            new NoVisibity().start();
        }
        System.out.println("main over");
    }
}
