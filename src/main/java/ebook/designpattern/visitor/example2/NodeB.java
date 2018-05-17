package ebook.designpattern.visitor.example2;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1015:38 <br>
 *
 * @author 周志辉
 */
public class NodeB implements Node{
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    public void action(){
        System.out.println("NodeB visited");
    }
}
