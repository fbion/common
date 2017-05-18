package test.recent;

/**
 * Created by zzh on 2015-12-06.
 */
public class Test12
{
    private String test;
    public A a;

    public Test12(String test)
    {
        this.test = test;
        this.a = new A();
    }

    public String getTest()
    {
        return a.getTest();
    }

    protected class A
    {
        public String getTest()
        {
            return Test12.this.test;
        }
    }
    public static void main(String[] args) {
        Test12 t = new Test12("test");
        Test12 t2 = new Test12("test2");
        t.a = t2.a;
        System.out.println(t.getTest());
    }
}
