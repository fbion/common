package ebook.designpattern.visitor.example2.test;

import ebook.designpattern.visitor.example2.Context;
import ebook.designpattern.visitor.example2.NodeA;
import ebook.designpattern.visitor.example2.NodeB;
import ebook.designpattern.visitor.example2.VisitA;
import ebook.designpattern.visitor.example2.VisitB;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1015:40 <br>
 *
 * @author 周志辉
 */
public class Client {
    private static Context ctx = new Context();

    public static void main(String[] args) {
        ctx.add(new NodeA());
        ctx.add(new NodeB());
        ctx.visit(new VisitA());
        ctx.visit(new VisitB());
    }
}
