package designpattern.memento.example1;

/**
 * 描述： <br>
 * 创建时间: 2017/8/109:23 <br>
 *
 * @author 周志辉
 */
public class Originator {
    private String state = "";

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public Memento createMemento(){
        return new Memento(this.state);
    }
    public void restoreMemento(Memento memento){
        this.setState(memento.getState());
    }
}
