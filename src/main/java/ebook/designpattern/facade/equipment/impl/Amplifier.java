package ebook.designpattern.facade.equipment.impl;

import ebook.designpattern.facade.equipment.Equipment;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Amplifier</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/5/4 16:00<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/5/4 16:00<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class Amplifier implements Equipment {

    private Tuner tuner;

    private DvdPlayer dvdPlayer;

    private CdPlayer cdPlayer;


    @Override
    public void on() {
        System.out.println("Amplifier on...");
    }


    @Override
    public void off() {
        System.out.println("Amplifier off...");
    }


    public void setCd() {

    }


    public void setDvd() {

    }


    public void setStereoSound() {

    }


    public void setSurroundSound() {

    }


    public void setTuner(Tuner tuner) {
        this.tuner = tuner;
    }


    public void setVolumn(int volumn) {

    }


    @Override
    public String toString() {
        return super.toString();
    }
}
