package ebook.thinking.chapter20.example002;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：UseCaseTracker</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/9 18:13<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/9 18:13<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class UseCaseTracker {
    public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
        for (Method method : cl.getDeclaredMethods()) {
            UseCase uc = method.getAnnotation(UseCase.class);
            if(uc != null) {
                System.out.println("Found Use Case : " + uc.id() + " " + uc.description());
                useCases.remove(new Integer(uc.id()));
            }
        }
        for (Integer id : useCases) {
            System.out.println("Warning: Missing use case-" + id);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }
}
