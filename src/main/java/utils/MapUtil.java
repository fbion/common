package utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：test<br/>
 * *********************************<br/>
 * <p>类名称：MapUtils</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/1 14:50<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/1 14:50<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class MapUtil<T, U> {
    private Map<T,U> map;

    public MapUtil() {
        map = new HashMap<>();
    }

    public MapUtil<T,U> put(T t, U u) {
        map.put(t, u);
        return this;
    }

    public Map<T,U> getMap() {
        return map;
    }
}
