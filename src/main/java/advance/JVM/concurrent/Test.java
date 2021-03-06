package advance.JVM.concurrent;

import java.util.Vector;

/**
 * 描述： 线程安全类Vector出现线程不安全的情况<br>
 * 创建时间: 2017/9/2518:07 <br>
 *
 * @author 周志辉
 */
public class Test {

    private static Vector<Integer> vector = new Vector<Integer>();


    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            }
            );
            Thread printThread = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    System.out.println((vector.get(i)));
                }
            });
            removeThread.start();
            printThread.start();
            //不要同时产生过多的线程，否则会导致操作系统假死
            while (Thread.activeCount() > 40) ;
        }
    }
}
