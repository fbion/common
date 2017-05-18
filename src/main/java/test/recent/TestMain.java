package test.recent;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2015/12/29.
 */
public class TestMain {

    /**
     * 线程池
     */
    static ExecutorService threadPool = new ThreadPoolExecutor(10, 100, 10,
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static Future<String> test(){
        Future<String> future = threadPool.submit((Callable<String>)()->{
                System.out.println("In callable()");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Callable() revive");
                return "Hello Java";});
        System.out.printf("In test()");
        return future;
    }

    public static void main(String[] args) throws InterruptedException {
//        threadPool.submit((Callable<String>)-> return "Hello World";)


        System.out.println("In main()");
        Future<String> future = test();
        System.out.println("return from test()");
        String s = null;
//        while(!future.isDone())
//        {
//            System.out.println("Not Done");
//            TimeUnit.MILLISECONDS.sleep(200);
//        }
//        System.out.println("Done");
        try {
            s = future.get(2, TimeUnit.SECONDS);
        }  catch (TimeoutException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
