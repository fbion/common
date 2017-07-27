package designpattern.multiple.example1.impl;

import designpattern.multiple.example1.Quackable;

/**
 * 描述： 鸭鸣器<br>
 * 创建时间: 2017/7/1817:06 <br>
 *
 * @author 周志辉
 */
public class DuckCall extends BaseDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Kwak");
    }
}
