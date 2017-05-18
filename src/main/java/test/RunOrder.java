package test;

/**
 * Created by Administrator on 2016/7/28.
 */
public class RunOrder {
    public static void main(String[] args) {
        Zi z = new Zi();
        System.out.println("main : " + z.getNum());
    }
}
class Fu{
    int x = 4;
    public Fu() {
        System.out.println("Fu : " + getNum());
    }
    private int getNum() {
        System.out.println("Fu getNum : " + x);
        return 100;
    }
}

class Zi extends Fu {
    int x = 5;
    public Zi() {
        System.out.println("Zi : " + getNum());
    }
    int getNum() {
        System.out.println("Zi getNum : " + x);
        return 200;
    }
}