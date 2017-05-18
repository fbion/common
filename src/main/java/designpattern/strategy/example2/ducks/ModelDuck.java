package designpattern.strategy.example2.ducks;

import designpattern.strategy.example2.Duck;
import designpattern.strategy.example2.behavior.impl.FlyNoWay;
import designpattern.strategy.example2.behavior.impl.Quake;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：ModelDuck</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/4/6 13:56<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/4/6 13:56<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class ModelDuck extends Duck {

    public ModelDuck() {
        super(new FlyNoWay(), new Quake());
    }
}
