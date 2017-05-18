package designpattern.facade.equipment.impl;

import designpattern.facade.equipment.Equipment;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Projector</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/5/4 16:13<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/5/4 16:13<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class Projector implements Equipment {

    private DvdPlayer dvdPlayer;


    public Projector(DvdPlayer dvdPlayer) {
        this.dvdPlayer = dvdPlayer;
    }


    @Override
    public void on() {

    }


    @Override
    public void off() {

    }


    public void tvMode() {}


    public void wideScreenMode() {}


    @Override
    public String toString() {
        return super.toString();
    }
}
