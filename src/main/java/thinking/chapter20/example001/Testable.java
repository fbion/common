package thinking.chapter20.example001;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Testable</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/9 17:33<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/9 17:33<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class Testable {
    public void execute() {
        System.out.println("Executing...");
    }

    @Test void testExecute() {
        execute();
    }
}
