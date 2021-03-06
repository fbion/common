package ebook.designpattern.facade;

import ebook.designpattern.facade.equipment.impl.Amplifier;
import ebook.designpattern.facade.equipment.impl.CdPlayer;
import ebook.designpattern.facade.equipment.impl.DvdPlayer;
import ebook.designpattern.facade.equipment.impl.PopcornPopper;
import ebook.designpattern.facade.equipment.impl.Projector;
import ebook.designpattern.facade.equipment.impl.Screen;
import ebook.designpattern.facade.equipment.impl.TheaterLights;
import ebook.designpattern.facade.equipment.impl.Tuner;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：HomeTheaterTestDriver</p>
 * *********************************<br/>
 * <p>类描述：HeadFirst设计模式中的外观模式例子</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/5/4 16:25<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/5/4 16:25<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class HomeTheaterTestDriver {

    public static void main(String[] args) {
        Amplifier amplifier = new Amplifier();
        Tuner tuner = new Tuner(amplifier);
        DvdPlayer dvd = new DvdPlayer(amplifier);
        CdPlayer cd = new CdPlayer(amplifier);
        Projector projector = new Projector(dvd);
        Screen screen = new Screen();
        TheaterLights lights = new TheaterLights();
        PopcornPopper pop = new PopcornPopper();
        HomeTheaterFacade facade = new HomeTheaterFacade(amplifier, tuner, cd, dvd, projector, lights, screen, pop);
        facade.watchMovie("God is crazy");
        facade.endMovie();
    }
}
