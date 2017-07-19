package designpattern.multiple.example1;

/**
 * 描述： <br>
 * 创建时间: 2017/7/1817:36 <br>
 *
 * @author 周志辉
 */
public class GooseAdapter implements Quackable {

    Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }


    @Override
    public void quack() {
        goose.honk();
    }
}
