package ebook.thinking.chapter20.example002;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：UseCase</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/9 17:46<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/9 17:46<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
    public int id();
    public String description() default "no description";
}
