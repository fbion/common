package test.temp;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Temp</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/14 8:43<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/14 8:43<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class Temp {
    static int i = 1;
    static {
        i = i-- + --i;
    }
    {
        i = i++ - ++i;
    }

    int test() {
        System.out.println("i = " + i);
        return i + i - i * i / i;
    }

    public static void main(String[] args) {
        System.out.println(new Temp().test());
    }
}
