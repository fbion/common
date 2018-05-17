package ebook.designpattern.visitor.example2;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1015:37 <br>
 *
 * @author 周志辉
 */
public interface Visitor{
    void visit(NodeA nodeA);
    void visit(NodeB nodeB);
}