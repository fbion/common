package ebook.datastructure;

/**
 * description: <br>
 * createTime: 2018/5/1711:30 <br>
 *
 * @author zzh
 */
public class StackDemo {

    public static String useStackToRevertString(String str) {
        MyStack stack = new MyStack(str.length());
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(useStackToRevertString("1234567890"));
    }
}
