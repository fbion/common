package designpattern;

import designpattern.command.example1.SimpleRemoteControl;
import designpattern.command.example1.commandimpl.LightOnCommand;
import designpattern.command.example1.equipment.Light;

/**
 * Created by Administrator on 2016/5/18.
 */
public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl rsc = new SimpleRemoteControl();
        rsc.setCommand(new LightOnCommand(new Light("")));
        rsc.buttonPressed();
    }
}
