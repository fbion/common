package thinking.chapter15.generate;

import java.util.Collection;

/**
 * Created by Administrator on 2016/4/29.
 */
public class Generators {
    public static <T> Collection<T> fill (Collection<T> collection, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++) {
            collection.add(gen.next());
        }
        return collection;
    }
}
