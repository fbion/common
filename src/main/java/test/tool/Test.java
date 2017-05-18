package test.tool;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/6/2.
 */
public class Test {
    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("js");
    public static void main(String[] args) throws ScriptException, InterruptedException {
        int n = 1000000;
        String str = "1+2*(+.5+2*(1+1.2/2))";
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            CalculateUtils.calculate(str);
        }
        System.out.println("CalculateUtils : " + (System.currentTimeMillis() - start));
        System.gc();
        TimeUnit.SECONDS.sleep(2);

        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            jse.eval(str);
        }
        System.out.println("jse : " + (System.currentTimeMillis() - start));
        System.gc();
        TimeUnit.SECONDS.sleep(2);

        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            CalculateMathematicExpressionUtils.calculate(str);
        }
        System.out.println("CalculateMathematicExpressionUtils : " + (System.currentTimeMillis() - start));
    }
}
/**
 * 结论Time(CalculateUtils)约等于Time(jse)/2约等于CalculateMathematicExpressionUtils/4
 */