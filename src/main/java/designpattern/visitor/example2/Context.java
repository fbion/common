package designpattern.visitor.example2;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：环境角色 <br>
 * 创建时间: 2017/8/1015:36 <br>
 *
 * @author 周志辉
 */
public class Context{
    List<Node> list = new ArrayList();
    public void add(Node node) {
        list.add(node);
    }
    public void visit(Visitor visitor) {
        for(Node node : list){
            node.accept(visitor);
        }
    }
}
