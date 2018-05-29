package advance.JVM.concurrent.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * description: <br>
 * createTime: 2018/5/2911:06 <br>
 *
 * @author zzh
 */
public class TestSimpleDateFormat {
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static ExecutorService es = Executors.newFixedThreadPool(10);


    public static void main(String[] args) {
        long timeStamp = System.currentTimeMillis()/ (86400 * 1000) * 86400 * 1000 - 8 * 3600 * 1000;
        for (int i = 0; i < 10; i++) {
            int t = i;
            es.submit(() -> {
                long temp = timeStamp - t * 86400 * 1000;
                for (int j = 0; j < 23; j++) {
                    StringBuilder sb = new StringBuilder();
                    for (int k = 0; k < t; k++) {
                        sb.append("\t");
                    }
                    temp += 3600 * 1000;
                    sb.append(SDF.format(new Date(temp)));
                    System.out.println(sb.toString());
                    try {
                        TimeUnit.MICROSECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
