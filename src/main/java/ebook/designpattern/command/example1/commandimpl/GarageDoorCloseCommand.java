package ebook.designpattern.command.example1.commandimpl;

import ebook.designpattern.command.example1.Command;
import ebook.designpattern.command.example1.equipment.GarageDoor;

/**
 * Created by Administrator on 2016/5/18.
 */
public class GarageDoorCloseCommand implements Command {
    GarageDoor door;

    public GarageDoorCloseCommand(GarageDoor door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.down();
        door.stop();
        door.lightOff();
    }

    @Override
    public void undo() {
        door.up();
        door.stop();
        door.lightOn();
    }
}
