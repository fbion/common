package ebook.designpattern.multiple.example1.composite;

import ebook.designpattern.multiple.example1.Quackable;

import java.util.ArrayList;

/**
 * 描述： <br>
 * 创建时间: 2017/7/1817:58 <br>
 *
 * @author 周志辉
 */
public class Flock implements Quackable {

    ArrayList<Quackable> quackers = new ArrayList<>();

    public void add(Quackable duck) {
        quackers.add(duck);
    }

    @Override
    public void quack() {
        for (Quackable quacker : quackers) {
            quacker.quack();
        }
    }
}
