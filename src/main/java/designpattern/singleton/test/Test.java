package designpattern.singleton.test;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1011:03 <br>
 *
 * @author 周志辉
 */
class TestSingleton implements Cloneable {
    private static final TestSingleton INSTANCE = new TestSingleton();

    private TestSingleton(){}

    public static TestSingleton getInstance() {
        return INSTANCE;
    }

    public TestSingleton clone(){
        TestSingleton singleton = null;
        try{
            singleton = (TestSingleton)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return singleton;
    }
}

public class Test {

    public static void main(String[] args) {
        TestSingleton s = TestSingleton.getInstance();
        TestSingleton s1 = s.clone();
        System.out.println(s);
        System.out.println(s1);
    }
}