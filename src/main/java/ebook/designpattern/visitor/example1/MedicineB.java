package ebook.designpattern.visitor.example1;

/**
 * 描述： 具体元素<br>
 * 创建时间: 2017/8/1014:52 <br>
 *
 * @author 周志辉
 */
public class MedicineB extends Medicine{

    public MedicineB(String name, double price) {
        super(name, price);
    }

    public void accept(Visitor visitor) {
        visitor.visitor(this);
    }
}
