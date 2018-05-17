package ebook.designpattern.memento.example1;

/**
 * 描述： <br>
 * 创建时间: 2017/8/109:23 <br>
 *
 * @author 周志辉
 */
public class Memento {
    private String state = "";
    public Memento(String state){
        this.state = state;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}
