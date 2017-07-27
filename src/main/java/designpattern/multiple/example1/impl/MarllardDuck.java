package designpattern.multiple.example1.impl;

import designpattern.multiple.example1.Quackable;

/**
 * 描述： 绿头鸭<br>
 * 创建时间: 2017/7/1817:01 <br>
 *
 * @author 周志辉
 */
public class MarllardDuck extends BaseDuck  implements Quackable {

    @Override
    public void quack() {
        System.out.println("Quack");
    }
}
