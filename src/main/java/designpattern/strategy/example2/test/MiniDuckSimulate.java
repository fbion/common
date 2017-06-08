package designpattern.strategy.example2.test;

import designpattern.strategy.example2.Duck;
import designpattern.strategy.example2.ducks.MallardDuck;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：MiniDuckSimulate</p>
 * *********************************<br/>
 * <p>类描述：HeadFirst设计模式中的策略模式例子</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/4/6 13:53<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/4/6 13:53<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class MiniDuckSimulate {

    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.performQuake();
        duck.performFly();
    }
}
