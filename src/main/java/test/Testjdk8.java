package test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Testjdk8</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/23 11:47<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/23 11:47<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class Testjdk8 {

    public static void testFunctionalInterface() {
        Supplier<String> personSupplier = String::new;
        System.out.println(personSupplier.get());

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        System.out.println(backToString.apply("123"));     // "123"
    }

    public static void main(String[] args) {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        long t0 = System.nanoTime();
        long count1 = values.stream().sorted().count();
        System.out.println(count1);

        long t1 = System.nanoTime();

        long millis1 = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis1));

        long t2 = System.nanoTime();
        long count2 = values.parallelStream().sorted().count();
        System.out.println(count2);

        long t3 = System.nanoTime();

        long millis2 = TimeUnit.NANOSECONDS.toMillis(t3 - t2);
        System.out.println(String.format("parallel sort took: %d ms", millis2));
    }
}
