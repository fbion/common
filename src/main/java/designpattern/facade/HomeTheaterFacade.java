package designpattern.facade;

import designpattern.facade.equipment.impl.Amplifier;
import designpattern.facade.equipment.impl.CdPlayer;
import designpattern.facade.equipment.impl.DvdPlayer;
import designpattern.facade.equipment.impl.PopcornPopper;
import designpattern.facade.equipment.impl.Projector;
import designpattern.facade.equipment.impl.Screen;
import designpattern.facade.equipment.impl.TheaterLights;
import designpattern.facade.equipment.impl.Tuner;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：HomeTheaterFacade</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/5/4 16:17<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/5/4 16:17<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class HomeTheaterFacade {

    private Amplifier amp;

    private Tuner tuner;

    private CdPlayer cdPlayer;

    private DvdPlayer dvdPlayer;

    private Projector projector;

    private TheaterLights theaterLights;

    private Screen screen;

    private PopcornPopper popcornPopper;


    public HomeTheaterFacade(Amplifier amp, Tuner tuner, CdPlayer cdPlayer, DvdPlayer dvdPlayer, Projector projector, TheaterLights theaterLights, Screen screen, PopcornPopper popcornPopper) {
        this.amp = amp;
        this.tuner = tuner;
        this.cdPlayer = cdPlayer;
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.theaterLights = theaterLights;
        this.screen = screen;
        this.popcornPopper = popcornPopper;
    }


    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        popcornPopper.on();
        popcornPopper.pop();
        theaterLights.dim(10);
        screen.down();
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setDvd();
        amp.setSurroundSound();
        amp.setVolumn(5);
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }


    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        popcornPopper.off();
        theaterLights.on();
        screen.up();
        projector.off();
        dvdPlayer.stop();
        dvdPlayer.eject();
        dvdPlayer.off();
    }
}
