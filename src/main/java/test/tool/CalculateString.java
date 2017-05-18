package test.tool;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Administrator on 2016/3/23.
 */
public class CalculateString {
//    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
    /**
     * js引擎，可以用jse.eval(表达式字符串)直接计算结果，当然还有别的用法，例如可以表达式里带变量，再指定变量值，计算结果
     */
    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("js");
    public static void main(String[] args) throws ScriptException {
        char a=37;
        int b=10;
        int c=9;
        int d;
        String str = ""+b + (char)a + c;

        char plus = '+';
        char multiply = '*';
        String expr = "x" + plus + "y" + multiply + "10";
        try {
            d = new Integer(jse.eval(str).toString());
            System.out.print(str + " = ");
            System.out.println(d);

            jse.put("x", 10);
            jse.put("y", 10);
            Object result = jse.eval(expr);
            System.out.print(expr + " = ");
            System.out.println(result);

            System.out.println(jse.eval("-1.0+2*2+6/-3-11"));

            System.out.println(jse.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
