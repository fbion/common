package test.recent;

/**
 * Created by Administrator on 2015/12/14.
 */
public class Test3 {


    public static void main(String[] args) {
        ((MyFunction)()-> System.out.println("Hello World")).run();
        new Object(){
            void show(){
                System.out.println("Hello Object!");
            }
        }.show();
    }
}

@FunctionalInterface
interface MyFunction{
    void run();
}