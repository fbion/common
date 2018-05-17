package ebook.thinking.chapter19;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：Food</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/7 14:18<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/7 14:18<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS
    }
    enum MainCourse implements Food {
        LASAGNE, BURRITO
    }
}
