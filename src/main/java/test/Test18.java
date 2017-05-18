package test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2016/5/28.
 */
public class Test18 {
    public static void main(String[] args) throws ParseException {
        List<Double> list = new ArrayList<>();
        list.add(456127987345.1293);
        list.add(4651456345345D);
        list.add(79812380124.9506);

        List<String> strList = list.stream().map((val) -> {
                NumberFormat formatter = new DecimalFormat("######.###");
                String str1 = formatter.format(val) + "";
                String[] temp = str1.split("\\.");
                NumberFormat formatter1 = new DecimalFormat("###,###");
                return formatter1.format(new Long(temp[0])) + (temp.length > 1 ? "." + temp[1] :"");
            }).collect(Collectors.toList());
        strList.forEach(System.out::println);

        String str = "1\2\3\4";
        System.out.println(str.replaceAll("\\\\", "\\"));
    }
}
