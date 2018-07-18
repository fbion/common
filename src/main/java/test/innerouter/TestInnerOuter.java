package test.innerouter;

import java.lang.reflect.Method;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：TestInnerOuter</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/20 10:45<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/20 10:45<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class TestInnerOuter {
    private static int i;

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(i);
            }
        };
        new Thread(runnable).start();

        for (Method method : TestInnerOuter.class.getDeclaredMethods()) {
            if(method.isSynthetic()) {
                System.out.println(method);
            }
        }
    }
}
