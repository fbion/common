package ebook.designpattern.command.example1.commandimpl;

import ebook.designpattern.command.example1.Command;
import ebook.designpattern.command.example1.equipment.Light;

/**
 * Created by Administrator on 2016/5/18.
 */
public class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }
}
