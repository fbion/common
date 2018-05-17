package ebook.designpattern.visitor.example1;

/**
 * 描述： 具体访问者：药房工作者<br>
 * 创建时间: 2017/8/1014:50 <br>
 *
 * @author 周志辉
 */
public class WorkerOfPharmacy extends Visitor{

    public void visitor(Medicine m) {
        System.out.println("药房工作者：" + name + "拿药 ：" + m.getName());
    }
}