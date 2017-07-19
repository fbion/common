package designpattern.multiple.example1.decorator;

import designpattern.multiple.example1.Quackable;

/**
 * 描述： <br>
 * 创建时间: 2017/7/1817:41 <br>
 *
 * @author 周志辉
 */
public class QuackCounter implements Quackable {

    Quackable duck;

    static int numberOfQuacks;


    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }


    @Override
    public void quack() {
        duck.quack();
        numberOfQuacks++;
    }

    public static int getQuacks() {
        return numberOfQuacks;
    }
}
