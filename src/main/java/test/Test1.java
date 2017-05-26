package test;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Administrator on 2016/7/27.
 */
public class Test1 {
    public static void sortByChineseCharacter() throws IllegalAccessException, InstantiationException {
        Comparator<Object> com= Collator.getInstance(java.util.Locale.CHINA);
        String[] newArray={"abc", "VIP", "中山","汕头","广州","安庆","阳江","南京","武汉","北京","安阳","北方"};
        Arrays.sort(newArray,com);
        for(String i:newArray){
            System.out.print( i + "  ");
        }
    }

    static void mytest(MyInterface i) {
        System.out.println(i.test());
    }


    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//        String s = "";
//        s += null;
//        System.out.println(s);
//        String s1 = null;
//        s1 += "";

        sortByChineseCharacter();
    }
}

interface MyInterface{
    String test();
}
