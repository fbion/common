package designpattern.memento.example1.test;

import designpattern.memento.example1.Caretaker;
import designpattern.memento.example1.Originator;

/**
 * 描述： <br>
 * 创建时间: 2017/8/109:24 <br>
 *
 * @author 周志辉
 */
public class Client {
    public static void main(String[] args){
        Originator originator = new Originator();
        originator.setState("状态1");
        System.out.println("初始状态:"+originator.getState());
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(originator.createMemento());
        originator.setState("状态2");
        System.out.println("改变后状态:"+originator.getState());
        originator.restoreMemento(caretaker.getMemento());
        System.out.println("恢复后状态:"+originator.getState());
    }
}
