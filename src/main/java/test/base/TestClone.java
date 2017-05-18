package test.base;

/**
 * Created by Administrator on 2016/6/24.
 */
public class TestClone implements Cloneable{
    private int i = 0;

    {
        System.out.println(i);
        i = 2;
    }

    public TestClone() {
        this.i = 5;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        TestClone clone = new TestClone();
        TestClone clone1 = (TestClone) clone.clone();
        System.out.println(clone1.i);
    }
}
