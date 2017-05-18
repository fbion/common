package designpattern.adapter.example1;

/**
 * Created by Administrator on 2016/5/20.
 */
public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("Fly");
    }
}
