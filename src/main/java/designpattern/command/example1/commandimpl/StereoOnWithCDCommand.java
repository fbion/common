package designpattern.command.example1.commandimpl;

import designpattern.command.example1.Command;
import designpattern.command.example1.equipment.Stereo;

/**
 * Created by Administrator on 2016/5/18.
 */
public class StereoOnWithCDCommand implements Command {
    Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);
    }

    @Override
    public void undo() {
        stereo.off();
    }
}
