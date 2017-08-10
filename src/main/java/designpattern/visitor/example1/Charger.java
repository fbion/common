package designpattern.visitor.example1;

/**
 * 描述： 具体访问者：划价员<br>
 * 创建时间: 2017/8/1014:49 <br>
 *
 * @author 周志辉
 */
public class Charger extends Visitor{

    public void visitor(Medicine m) {
        System.out.println("划价员：" + name +"给药" + m.getName() +"划价:" + m.getPrice());
    }
}
