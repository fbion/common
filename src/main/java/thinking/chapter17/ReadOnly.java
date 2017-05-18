package thinking.chapter17;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by Administrator on 2016/3/21.
 */
public class ReadOnly {
    static Collection<Integer> data = new ArrayList<>();
    static {
        for (int i = 0; i < 10; i++) {
            data.add(i);
        }
    }

    public static void main(String[] args) {
        Collection<Integer> c = Collections.unmodifiableCollection(data);
        System.out.println(c);
        System.out.println(c.getClass().getSimpleName());
    }
}
