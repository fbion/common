package designpattern.command.example1.commandimpl;

import designpattern.command.example1.Command;
import designpattern.command.example1.equipment.Stereo;

/**
 * Created by Administrator on 2016/5/18.
 */
public class StereoOffCommand implements Command {
    Stereo stereo;

    public StereoOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();
    }

    @Override
    public void undo() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }
}
