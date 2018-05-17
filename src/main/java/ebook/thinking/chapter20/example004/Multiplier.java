package ebook.thinking.chapter20.example004;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Multiplier</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/14 17:15<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/14 17:15<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
@ExtractInterface("IMultiplier")
public class Multiplier {
    public int multiply(int x, int y) {
        int total = 0;
        for (int i = 0; i < x; i++) {
            total = add(total, y);
        }
        return total;
    }

    private int add(int x, int y) {
        return x + y;
    }


    public static void main(String[] args) {
        Multiplier m = new Multiplier();
        System.out.println("11*16 = " + m.multiply(11, 16));
    }
}
