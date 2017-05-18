package utils.file;

import java.io.IOException;

/**
 * Created by Administrator on 2016/8/9.
 */

@FunctionalInterface
public interface IOperator {
    boolean operator(String basePath, String relativePath, String... otherString) throws IOException;
}
