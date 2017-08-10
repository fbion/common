package designpattern.visitor.example2;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1015:37 <br>
 *
 * @author 周志辉
 */
public class VisitB implements Visitor{
    public void visit(NodeA nodeA){
        System.out.println("***visitB***");
        nodeA.action();
    }
    public void visit(NodeB nodeB){
        System.out.println("***visitB***");
        nodeB.action();
    }
}
