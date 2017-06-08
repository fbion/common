package designpattern.command.example1;

import designpattern.command.example1.commandimpl.LightOnCommand;
import designpattern.command.example1.equipment.Light;

/**
 * Created by Administrator on 2016/5/18.
 * HeadFirst设计模式中的命令模式例子
 */
public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl rsc = new SimpleRemoteControl();
        rsc.setCommand(new LightOnCommand(new Light("")));
        rsc.buttonPressed();
    }
}
