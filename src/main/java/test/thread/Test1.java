package test.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * description： <br>
 * createTime: 2017/10/1910:44 <br>
 *
 * @author zzh
 */
public class Test1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newSingleThreadExecutor();
        Future future = es.submit(() ->  "hello world" );
//        while(!future.isDone()) {
//            System.out.println("sleep");
//            TimeUnit.SECONDS.sleep(1);
//        }
        System.out.println(future.get());
        System.out.println(future.getClass());
    }

}
