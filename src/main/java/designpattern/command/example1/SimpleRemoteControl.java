package designpattern.command.example1;

/**
 * Created by Administrator on 2016/5/18.
 */
public class SimpleRemoteControl {
    Command slot;

    public SimpleRemoteControl() {
    }

    public void setCommand(Command slot) {
        this.slot = slot;
    }

    public void buttonPressed() {
        slot.execute();
    }
}
