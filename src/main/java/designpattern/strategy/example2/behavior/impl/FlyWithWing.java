package designpattern.strategy.example2.behavior.impl;

import designpattern.strategy.example2.behavior.FlyBehavior;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：FlyWithWing</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/4/6 13:44<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/4/6 13:44<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class FlyWithWing implements FlyBehavior {

    @Override
    public void fly() {
        System.out.println("I'm flying!");
    }
}
