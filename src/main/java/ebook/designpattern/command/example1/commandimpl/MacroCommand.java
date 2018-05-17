package ebook.designpattern.command.example1.commandimpl;

import ebook.designpattern.command.example1.Command;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：MacroCommand</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/5/3 11:56<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/5/3 11:56<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class MacroCommand implements Command {
    Command[] commands;


    public MacroCommand(Command[] commands) {
        this.commands = commands;
    }


    @Override
    public void execute() {
        for (int i = 0; i < commands.length; i++) {
            commands[i].execute();
        }
    }


    @Override
    public void undo() {
        for (int i = commands.length - 1; i >= 0; i--) {
            commands[i].execute();
        }
    }
}
