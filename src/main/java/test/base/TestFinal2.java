package test.base;

/**
 * Created by Administrator on 2016/7/23.
 */
public class TestFinal2 {
    public static void main(String[] args) {
        System.out.println(Final.i);
    }
}
class Final{
    static final int i = 1;
    static {
        System.out.println("Class Loaded");
    }
}