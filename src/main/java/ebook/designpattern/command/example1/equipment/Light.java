package ebook.designpattern.command.example1.equipment;

/**
 * Created by Administrator on 2016/5/18.
 */
public class Light {
    private String name;

    public Light(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(name + "Light On");
    }

    public void off() {
        System.out.println(name + "Light Off");
    }
}
