package thinking.chapter19;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Course</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/7 14:21<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/7 14:21<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public enum Course {
    APPETIZER(Food.Appetizer.class);

    private Food[] values;
    private Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }
    public Food randomSelection() {
        return Enums.random(values);
    }
}
