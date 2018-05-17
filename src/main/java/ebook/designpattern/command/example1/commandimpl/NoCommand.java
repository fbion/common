package ebook.designpattern.command.example1.commandimpl;

import ebook.designpattern.command.example1.Command;

/**
 * Created by Administrator on 2016/5/18.
 */
public class NoCommand implements Command {
    @Override
    public void execute() {
        System.out.println("Bind No Command.");
    }

    @Override
    public void undo() {
        System.out.println("Bind No Command.");
    }
}
