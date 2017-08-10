package designpattern.visitor.example1;

/**
 * 描述： 具体访问者：划价员<br>
 * 创建时间: 2017/8/1014:49 <br>
 *
 * @author 周志辉
 */
public class Charger extends Visitor{

    public void visitor(MedicineA a) {
        System.out.println("划价员：" + name +"给药" + a.getName() +"划价:" + a.getPrice());
    }

    public void visitor(MedicineB b) {
        System.out.println("划价员：" + name +"给药" + b.getName() +"划价:" + b.getPrice());
    }

}
