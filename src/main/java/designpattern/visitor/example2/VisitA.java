package designpattern.visitor.example2;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1015:37 <br>
 *
 * @author 周志辉
 */
public class VisitA implements Visitor{
    public void visit(NodeA nodeA){
        System.out.println("***visitA***");
        nodeA.action();
    }
    public void visit(NodeB nodeB){
        System.out.println("***visitA***");
        nodeB.action();
    }
}
