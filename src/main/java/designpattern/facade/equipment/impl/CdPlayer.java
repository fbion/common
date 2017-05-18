package designpattern.facade.equipment.impl;

import designpattern.facade.equipment.Equipment;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：CdPlayer</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/5/4 16:06<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/5/4 16:06<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class CdPlayer implements Equipment {

    private Amplifier amplifier;


    public CdPlayer(Amplifier amplifier) {
        this.amplifier = amplifier;
    }


    @Override
    public void on() {

    }


    @Override
    public void off() {

    }


    public void eject() {}


    public void pause() {}


    public void play () {}


    public void stop() {}


    @Override
    public String toString() {
        return super.toString();
    }
}
