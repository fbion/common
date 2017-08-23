package advance.JVM;

/**
 * 描述： <br>
 * 创建时间: 2017/8/239:20 <br>
 *
 * @author 周志辉
 */
public class StaticDispatch {
    static abstract class Human{}

    static class Man extends Human{}

    static class Woman extends Human{}

    public void sayHello(Human guy){
        System.out.println("Hello, guy!");
    }

    public void sayHello(Man guy){
        System.out.println("Hello, gentleman!");
    }

    public void sayHello(Woman guy){
        System.out.println("Hello, lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(woman);
        sd.sayHello(new Man());
        sd.sayHello(new Woman());
    }
}
