package advance.JVM.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * 描述： this引用逃逸，常发生在构造器里启动线程或注册监听器的情景<br>
 * 创建时间: 2017/9/2610:29 <br>
 *
 * @author 周志辉
 */
public class ThisEscape {
    private int i;
    public ThisEscape() {
        new Thread(new EscapeRunnable()).start();
        // ...
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i = 1;
    }
    private class EscapeRunnable implements Runnable {

        @Override
        public void run() {
            // 通过ThisEscape.this就可以引用外围类对象, 但是此时外围类对象可能还没有构造完成, 即发生了外围类的this引用的逃逸
            System.out.println(ThisEscape.this.i);//输出未正确初始化的i值
        }
    }
    public int getI() {
        return i;
    }
    public static void main(String[] args) {
        System.out.println(new ThisEscape().getI());//输出正确初始化的i值
    }
}
