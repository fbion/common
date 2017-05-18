package test.tool;

import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/4/20.
 */
public class CalculateMathematicExpressionUtils {
    /**
     * 圆括号匹配正则
     */
    static final Pattern PARENTHESIS_PATTERN = Pattern.compile("\\([^()]*\\)");

    /**
     * 简单计算式匹配正则
     */
    static final Pattern SIMPLE_CALUATE_PATTERN = Pattern.compile("\\(?([+-])?\\d*(\\.\\d+)?([/+\\-*%]([+-])?\\d*(\\.\\d+)?)*\\)?");

    /**
     * 简单一级（乘除）计算式匹配正则
     */
    static final Pattern FIRST_LEVEL_PATTERN = Pattern.compile("(((\\d+(\\.\\d+)?)|(\\.\\d+)))([*/%])(([+-])?((\\d+(\\.\\d+)?)|(\\.\\d+)))");

    /**
     * 简单二级（加减）计算式匹配正则
     */
    static final Pattern SECOND_LEVEL_PATTERN = Pattern.compile("(([+-])?((\\d+(\\.\\d+)?)|(\\.\\d+)))([+-])(([+-])?((\\d+(\\.\\d+)?)|(\\.\\d+)))");

    /**
     * 递归圆括号，提取简单表达式，验证并计算
     * 如果非法算术表达式返回null,否则返回计算结果，被0除抛异常
     * @param str
     * @return
     * @throws ScriptException
     */
    public static Double calculate(String str) throws ArithmeticException {
        str = str.replaceAll("\\s+", "");
        Matcher m = PARENTHESIS_PATTERN.matcher(str);
        while(m.find()) {
            String temp = m.group();
            if(!simpleCalculate(temp)) {
                return null;
            } else {
                str = str.replace(temp, calculateSimple(temp.replaceAll("\\(", "").replaceAll("\\)", "")).toString());
            }
            m = PARENTHESIS_PATTERN.matcher(str);
        }
        if(str.indexOf("(") < 0 && str.indexOf(")") < 0 && simpleCalculate(str)) {
            return calculateSimple(str);
        }
        return null;
    }

    /**
     * 判断给定字符串是不是一个简单的运算式
     * @param src
     * @return
     */
    public static boolean simpleCalculate(String src) {
        return SIMPLE_CALUATE_PATTERN.matcher(src.replaceAll("\\s", "")).matches();
    }

    /**
     * 计算简单计算式
     * @param str
     * @return
     * @throws ScriptException
     */
    private static Double calculateSimple(String str) throws ArithmeticException {
        Matcher m = null;
        //先计算计算式中的乘除余
        m = FIRST_LEVEL_PATTERN.matcher(str);
        while(m.find()) {
            str = str.replace(m.group(), caculateBase(m.group(1), m.group(6), m.group(7)) + "");
            m = FIRST_LEVEL_PATTERN.matcher(str);
        }
        //再计算计算式中的加减
        m = SECOND_LEVEL_PATTERN.matcher(str);
        while(m.find()) {
            str = str.replace(m.group(), caculateBase(m.group(1), m.group(7), m.group(8)) + "");
            m = SECOND_LEVEL_PATTERN.matcher(str);
        }
        return Double.parseDouble(str);
    }

    /**
     * 计算基本算式，加减乘除余
     * @param operand1
     * @param operator
     * @param operand2
     * @return
     * @throws ArithmeticException
     */
    private static Double caculateBase(String operand1, String operator, String operand2) throws ArithmeticException {
//        System.out.println("operand1 = " + operand1);
//        System.out.println("operator = " + operator);
//        System.out.println("operand2 = " + operand2 + "\n");
        double d1 = Double.parseDouble(operand1);
        double d2 = Double.parseDouble(operand2);
        switch (operator) {
            case "*":
                return d1 * d2;
            case "/":
                //可省，被0除JVM会抛出此异常
                if(d2 == 0) {
                    throw new ArithmeticException("Divided by zero");
                } else {
                    return d1 / d2;
                }
            case "%":
                //可省，被0除JVM会抛出此异常
                if(d2 == 0) {
                    throw new ArithmeticException("Divided by zero");
                } else {
                    return d1 % d2;
                }
            case "+":
                return d1 + d2;
            case "-":
                return d1 - d2;
            default:
                return null;
        }
    }

    public static void main(String[] args) throws ScriptException {
        String str1 = "1* (2 +.3 )";
        System.out.println(calculate(str1));
    }
}