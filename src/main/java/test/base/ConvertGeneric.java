package test.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/30.
 */
public class ConvertGeneric {
    public static <T> List<T> convert(List<?> list, Class<T> c) {
        return (List<T>)list;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("123");
        List<Object> b = convert(list, Object.class);
//        List<Object> c = (List<Object>)list;
        b.add("456");
        System.out.println(b);
        b.add(123);
        b.add(new Object());
        System.out.println(b);
    }
}
