package test.recent;

/**
 * Created by Administrator on 2015/11/28.
 */
class Parent
{
    int x = 10;

    public Parent()
    {
        add(2);
    }

    void add(int y)
    {
        System.out.println(x);
        System.out.println("p");
        x += y;
    }

    public int getX()
    {
        return x;
    }
}
class Child extends Parent
{
    int x = 9;

    void add(int y)
    {
        System.out.println(x);
        System.out.println("c");
        x += y;
    }

    public static void main(String[] args)
    {
        Parent p = new Child();
        Child c = (Child)p;
        System.out.println(p.x);
        System.out.println(p.getX());
        System.out.println(c.x);
        System.out.println(c.getX());
    }
}
