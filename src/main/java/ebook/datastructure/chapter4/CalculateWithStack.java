package ebook.datastructure.chapter4;

/**
 * description: <br>
 * createTime: 2018/5/1811:53 <br>
 *
 * @author zzh
 */
public class CalculateWithStack {

    MyStack<Object> stack;

    String expression;


    public CalculateWithStack(String expression) {
        this.expression = expression;
        stack  = new MyStack<>(expression.length()/ 2);
    }


    public static double calculate() {


        return 0;
    }


    public static void main(String[] args) {
    }
}
