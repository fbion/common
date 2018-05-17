package ebook.designpattern.command.example1.equipment;

/**
 * Created by Administrator on 2016/5/18.
 */
public class GarageDoor {
    public void up() {
        System.out.println("Garage up");
    }

    public void down() {
        System.out.println("Garage down");
    }

    public void stop() {
        System.out.println("Garage stop");
    }

    public void lightOn() {
        System.out.println("Garage lightOn");
    }

    public void lightOff() {
        System.out.println("Garage lightOff");
    }
}
