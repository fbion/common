package designpattern.command.example1.commandimpl;

import designpattern.command.example1.Command;
import designpattern.command.example1.equipment.Light;

/**
 * Created by Administrator on 2016/5/18.
 */
public class LightOffCommand implements Command {
    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
