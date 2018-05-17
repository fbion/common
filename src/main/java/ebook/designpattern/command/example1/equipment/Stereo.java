package ebook.designpattern.command.example1.equipment;

/**
 * Created by Administrator on 2016/5/18.
 */
public class Stereo {
    private int volume;

    public void on() {
        System.out.println("Stereo on");
    }

    public void off() {
        System.out.println("Stereo off");
    }

    public void setCD() {
        System.out.println("Stereo setCD");
    }

    public void setDVD() {
        System.out.println("Stereo setDVD");
    }

    public void setRadio() {
        System.out.println("Stereo setRadio");
    }

    public void setVolume(int volume) {
        System.out.println("Stereo setVolume : " + volume);
        this.volume = volume;
    }
}
