package thinking.chapter21.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/3/28.
 */
public class ExceptionThread implements Runnable {
    @Override
    public void run() {
        throw new RuntimeException();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        //无法捕获其他进程中的异常
        try{
            exec.execute(new ExceptionThread());
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
