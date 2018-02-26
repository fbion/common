package designpattern.visitor.example2;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1015:38 <br>
 *
 * @author 周志辉
 */
public interface Node{
    void accept(Visitor visitor);
}
