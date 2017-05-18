package test.tool;

import javax.script.ScriptException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/27.
 */
public class CalculateUtils {

    /**
     * 递归圆括号，提取简单表达式，验证并计算
     * 如果非法算术表达式返回null,否则返回计算结果，被0除抛异常
     * @param str
     * @return
     * @throws ScriptException
     */
    public static Double calculate(String str) {

        //循环找最里层的一对（）及其内的计算式，找出后对其内的计算式调用calculateSimple计算，并把找到的()及表达式替换成计算结果
        //输入表达式存在很多种非法情况，如()没有成对，或顺序不对
        while(str.contains(")") || str.contains(")")) {
            int i1 = str.indexOf(")");
            if(i1 < 0) {
                return null;
            }
//            System.out.println(i1);
            String temp = str.substring(0, i1 + 1);
            int i2 = temp.lastIndexOf("(");
            if(i2 < 0) {
                return null;
            }
            temp = temp.substring(i2);
//            System.out.println(temp);
            Double d = calculateSimple(temp.replaceAll("\\(", "").replaceAll("\\)", ""));
            if(d ==null) {
                return null;
            } else {
                //replaceAll第一个参数是正则，如果temp里没有特殊符号就直接用temp就行，
                // 但是这里temp里有()这两个在正则里是元字符所以，用Pattern.quote(temp)来生成合法正则
                str = str.replaceAll(Pattern.quote(temp), d + "");
            }
        }
        return calculateSimple(str.replaceAll("\\(", "").replaceAll("\\)", ""));
    }

    /**
     * 计算简单计算式
     * @param str
     * @return
     * @throws ScriptException
     */
    private static Double calculateSimple(String str) throws ArithmeticException {
        //也可以改用数组操作，但是
        //用来按顺序保存操作数
        LinkedList<Double> operands = new LinkedList<>();
        //用来按顺序保存操作符
        LinkedList<String> operators = new LinkedList<>();
        Double result = null;
        int index = 0;
        char[] chars = str.toCharArray();
        while(index < chars.length) {
            //从字符数组中取出计算数放到计算数列表
            int start = index;
            if(chars[index] == '+' || chars[index] == '-') {
                index++;
            }
            while(index < chars.length && (chars[index] == '.' || (chars[index] >= '0' &&chars[index] <= '9'))) {
                index++;
            }
            try {
                //如果取到为空串或格式不对，调用Double构造器会抛异常，这说明表达式不是合法的算式，所以返回null
                Double operand = new Double(new String(chars, start, index  - start));
                operands.add(operand);
            } catch (Exception e) {
                return null;
            }
            //取运算符放入到运算符列表，如果index超过数组最大序号则说明没有运算符了，上面取到的为最后一个操作数
            if(index < chars.length && (chars[index] == '+' || chars[index] == '-' || chars[index] == '*' || chars[index] == '/' || chars[index] == '%')) {
                operators.add(new String(chars, index, 1));
                index++;
            }
        }

        //没取到运算符，就返回计算式列表里的第一个计算式
        //应该不会出现没有运算符而有多个计算数的情况
        if(operators.size() < 1) {
            return operands.get(0);
        } else {
            //操作数应比操作符多一个，否则表达式不全，即返回null
            if(operators.size() > operands.size() - 1) {
                return null;
            }
            for (int i = 0; i < operators.size(); i++) {
                //先遍历操作符，把*,/,%先进行计算
                switch (operators.get(i)) {
                    case "*":
                        operandsOperation(operands, i, operands.get(i) * operands.get(i+1));
                        operators.remove(i);
                        break;
                    case "/":
                        if(operands.get(i+1) == 0) {
                            throw new ArithmeticException("Divided by zero");
                        }
                        operandsOperation(operands, i, operands.get(i) / operands.get(i+1));
                        operators.remove(i);
                        break;
                    case "%":
                        if(operands.get(i+1) == 0) {
                            throw new ArithmeticException("Divided by zero");
                        }
                        operandsOperation(operands, i, operands.get(i) % operands.get(i+1));
                        operators.remove(i);
                        break;
                    default:
                        break;
                }
            }

            //遍历操作符，把+，-先进行计算
            for (int i = 0; i < operators.size(); i++) {
                switch (operators.get(i)) {
                    case "+":
                        if(operands.get(i+1) == 0) {
                            throw new ArithmeticException("Divided by zero");
                        }
                        operandsOperation(operands, i, operands.get(i) + operands.get(i+1));
                        operators.remove(i);
                        break;
                    case "-":
                        if(operands.get(i+1) == 0) {
                            throw new ArithmeticException("Divided by zero");
                        }
                        operandsOperation(operands, i, operands.get(i) + operands.get(i+1));
                        operators.remove(i);
                        break;
                    default:
                        break;
                }
            }
        }
        //返回第一个操作数作为结果
        return operands.get(0);
    }

    /**
     * 把新生成的计算数添加到列表，并把已经计算过的计算数删除掉
     * @param operands
     * @param i
     * @param newOperand
     */
    private static void operandsOperation(LinkedList<Double> operands, int i, double newOperand) {
        operands.add(i, newOperand);
        //这里有个坑需要注意
        // 就是要删除序号为i+1和i+2的元素，如果先删i+1，再删i+2的话
        // 因为删除i+1后后面元素都的序号都因为前面少了一个元素而减小了1所以会导致删错元素
        //可以删除两遍i+1元素，所以可先删后面的i+2，i+1
        //在通过fori循环通过序号删除集合的元素时都要注意，随着删除元素后剩余元素的序号改变而导致执行结果不是想要的
        //通过iterator遍历删除则可能因为调用方法的顺序不对导致会报一个异常，这个具体得看源码了，我也不记得了
        operands.remove(i + 2);         //operands.remove(i + 1);效果相同
        operands.remove(i + 1);
    }

    public static void main(String[] args) {
        System.out.println(calculate("1+2*(+.5+2*(1+1.2/2))"));
    }
}
