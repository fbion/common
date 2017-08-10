package designpattern.visitor.example1;

/**
 * 描述： 抽象元素<br>
 * 创建时间: 2017/8/1014:50 <br>
 *
 * @author 周志辉
 */
public abstract class Medicine {
    protected String name;
    protected double price;

    public Medicine (String name,double price){
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract void accept(Visitor visitor);
}
