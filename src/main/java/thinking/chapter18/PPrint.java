package thinking.chapter18;

import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Administrator on 2016/3/21.
 */
public class PPrint {
    public static String promat(Collection<?> c)
    {
        if(c.size() == 0)
        {
            return null;
        }
        StringBuilder result = new StringBuilder("[");
        for (Object item : c) {
            if(c.size() != 1)
            {
                result.append("\n  ");
            }
            result.append(item);
        }
        if(c.size() != 1)
        {
            result.append("\n  ");
        }
        result.append("]");
        return result.toString();
    }

    public static void pprint(Object[] c)
    {
        System.out.println(promat(Arrays.asList(c)));
    }
}
