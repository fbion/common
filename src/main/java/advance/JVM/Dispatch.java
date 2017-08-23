package advance.JVM;

/**
 * 描述： <br>
 * 创建时间: 2017/8/2310:44 <br>
 *
 * @author 周志辉
 */
public class Dispatch {
    static class QQ{}
    static class _360{}
    public static class Father{
        public void hardChoice(QQ arg) {
            System.out.println("father choose qq");
        }
        public void hardChoice(_360 arg) {
            System.out.println("father choose 360");
        }
    }
    public static class Son extends Father{
        public void hardChoice(QQ arg) {
            System.out.println("son choose qq");
        }
        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }
    }


    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());
        son.hardChoice(new QQ());
    }
}
