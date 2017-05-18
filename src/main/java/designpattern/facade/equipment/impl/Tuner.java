package designpattern.facade.equipment.impl;

import designpattern.facade.equipment.Equipment;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Turner</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/5/4 15:55<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/5/4 15:55<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class Tuner implements Equipment {

    private Amplifier amplifier;


    public Tuner(Amplifier amplifier) {
        this.amplifier = amplifier;
    }


    @Override
    public void on() {
        System.out.println("Tuner on...");
    }


    @Override
    public void off() {
        System.out.println("Tuner off...");
    }


    public void setAm() {
        System.out.println("Tuner setAm...");
    }


    public void setFm() {
        System.out.println("Tuner setFm...");
    }


    public void setFrequncy() {
        System.out.println("Tuner setFrequncy...");
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
