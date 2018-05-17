package ebook.designpattern.strategy.example2;

import ebook.designpattern.strategy.example2.behavior.FlyBehavior;
import ebook.designpattern.strategy.example2.behavior.QuakeBehavior;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Duck</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/4/6 13:41<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/4/6 13:41<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public abstract class Duck {
    FlyBehavior flyBehavior;

    QuakeBehavior quakeBehavior;


    public Duck(FlyBehavior flyBehavior, QuakeBehavior quakeBehavior) {
        this.flyBehavior = flyBehavior;
        this.quakeBehavior = quakeBehavior;
    }


    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuake() {
        quakeBehavior.quake();
    }

    public void swim() {
        System.out.println("All ducks float,even decoys!");
    }


    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }


    public void setQuakeBehavior(QuakeBehavior quakeBehavior) {
        this.quakeBehavior = quakeBehavior;
    }
}
