package designpattern.command.example1.commandimpl;

import designpattern.command.example1.Command;
import designpattern.command.example1.equipment.GarageDoor;

/**
 * Created by Administrator on 2016/5/18.
 */
public class GarageDoorOpenCommand implements Command {
    GarageDoor door;

    public GarageDoorOpenCommand(GarageDoor door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.up();
        door.stop();
        door.lightOn();
    }

    @Override
    public void undo() {
        door.down();
        door.stop();
        door.lightOff();
    }
}
