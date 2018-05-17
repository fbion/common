package ebook.designpattern.command.example1;

import ebook.designpattern.command.example1.commandimpl.GarageDoorCloseCommand;
import ebook.designpattern.command.example1.commandimpl.GarageDoorOpenCommand;
import ebook.designpattern.command.example1.commandimpl.LightOffCommand;
import ebook.designpattern.command.example1.commandimpl.LightOnCommand;
import ebook.designpattern.command.example1.commandimpl.StereoOffCommand;
import ebook.designpattern.command.example1.commandimpl.StereoOnWithCDCommand;
import ebook.designpattern.command.example1.equipment.GarageDoor;
import ebook.designpattern.command.example1.equipment.Light;
import ebook.designpattern.command.example1.equipment.Stereo;

/**
 * Created by Administrator on 2016/5/18.
 */
public class RemoteLoader {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        Light bedRoomLight = new Light("Bed Room");
        GarageDoor door = new GarageDoor();
        Stereo stereo = new Stereo();

        LightOnCommand livingRoomLightOnCommand = new LightOnCommand(livingRoomLight);
        LightOffCommand livingRoomLightOffCommand = new LightOffCommand(livingRoomLight);
        LightOnCommand kitchenLightOnCommand = new LightOnCommand(kitchenLight);
        LightOffCommand kitchenLightOffCommand = new LightOffCommand(kitchenLight);
        LightOnCommand bedRoomLightOnCommand = new LightOnCommand(bedRoomLight);
        LightOffCommand bedRoomLightOffCommand = new LightOffCommand(bedRoomLight);

        GarageDoorOpenCommand doorOpenCommand = new GarageDoorOpenCommand(door);
        GarageDoorCloseCommand doorcloseCommand = new GarageDoorCloseCommand(door);

        StereoOnWithCDCommand stereoOnWithCDCommand = new StereoOnWithCDCommand(stereo);
        StereoOffCommand stereoOffCommand = new StereoOffCommand(stereo);

        remoteControl.setCommand(0, livingRoomLightOnCommand, livingRoomLightOffCommand);
        remoteControl.setCommand(1, kitchenLightOnCommand, kitchenLightOffCommand);
        remoteControl.setCommand(2, bedRoomLightOnCommand, bedRoomLightOffCommand);
        remoteControl.setCommand(3, doorOpenCommand, doorcloseCommand);
        remoteControl.setCommand(4, stereoOnWithCDCommand, stereoOffCommand);

        remoteControl.onButtonPressed(0);
        remoteControl.onButtonPressed(1);
        remoteControl.onButtonPressed(2);
        remoteControl.onButtonPressed(3);
        remoteControl.onButtonPressed(4);
        remoteControl.onButtonPressed(5);
        remoteControl.onButtonPressed(6);
        remoteControl.offButtonPressed(0);
        remoteControl.offButtonPressed(1);
        remoteControl.offButtonPressed(2);
        remoteControl.offButtonPressed(3);
        remoteControl.offButtonPressed(4);
        remoteControl.offButtonPressed(5);
        remoteControl.offButtonPressed(6);
    }
}
