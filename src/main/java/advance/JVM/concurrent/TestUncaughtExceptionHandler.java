package advance.JVM.concurrent;

/**
 * description: <br>
 * createTime: 2017/11/1417:40 <br>
 *
 * @author zzh
 */
public class TestUncaughtExceptionHandler {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            throw new RuntimeException("2222");
        });
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(e.getMessage());
        });
        thread.start();
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println("Excpetion Name:" + e.getClass().getName());
            System.out.println("Message:" + e.getMessage());
            for (StackTraceElement traceElement : e.getStackTrace()) {
                System.err.println(traceElement.toString());
            }
        });
        new Thread(() -> {
            throw new RuntimeException("3333");
        }).start();
        System.out.println("main over");
        throw new RuntimeException("111111");
    }
}
