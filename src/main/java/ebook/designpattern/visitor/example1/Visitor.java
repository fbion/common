package ebook.designpattern.visitor.example1;

/**
 * 描述： 抽象访问者<br>
 * 创建时间: 2017/8/1014:49 <br>
 *
 * @author 周志辉
 */
public abstract class Visitor {
    protected String name;

    public void setName(String name) {
        this.name = name;
    }


    public abstract void visitor(Medicine m);
}
