package ebook.designpattern.memento.example1;

/**
 * 描述： <br>
 * 创建时间: 2017/8/109:24 <br>
 *
 * @author 周志辉
 */
public class Caretaker {
    private Memento memento;
    public Memento getMemento(){
        return memento;
    }
    public void setMemento(Memento memento){
        this.memento = memento;
    }
}
