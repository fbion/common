package test.recent;

class ParentTest
{
    static int y = 2;

    int yy = 3;
    static
    {
        System.out.println("parentTest y = " + y);
    }
    {
        ++y;
    }

    ParentTest()
    {
        System.out.println("test.recent.ParentTest construction y = " + y);
    }
}

public class Test extends ParentTest
{
    static int x = 1;

    static String s = "D/123";
    static
    {
        if (s.equals("D/123"))
            s = "345";
        if (x == 1)
        {
            x = 2;
        }
    }
    {
        System.out.println("<init>");
        if (s.equals("345"))
            s = "678";
        if (x == 2)
            x = 3;
        ++x;
    }

    public Test()
    {
        System.out.println(x);
        System.out.println(s);
    }

    public Test(String ss)
    {
        System.out.println(x);
        System.out.println(s);
    }

    public static void main(String[] args)
    {
        new Test();
        System.out.println();
        new Test("sssss");
        // test.recent.Test t = new test.recent.Test("333");
        // System.out.println(t.x);
        // System.out.println(test.recent.Test.s);
    }

}