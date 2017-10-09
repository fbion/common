package tools.xiami;

import java.io.File;

/**
 * Created by Administrator on 2016/8/9.
 */

@FunctionalInterface
public interface IOperator {
    void operator(File file);
}
