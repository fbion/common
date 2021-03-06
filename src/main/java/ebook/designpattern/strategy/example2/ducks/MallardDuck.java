package ebook.designpattern.strategy.example2.ducks;

import ebook.designpattern.strategy.example2.Duck;
import ebook.designpattern.strategy.example2.behavior.impl.FlyWithWing;
import ebook.designpattern.strategy.example2.behavior.impl.Quake;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：MallardDuck</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/4/6 13:48<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/4/6 13:48<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class MallardDuck extends Duck {

    public MallardDuck() {
        super(new FlyWithWing(), new Quake());
    }
}
