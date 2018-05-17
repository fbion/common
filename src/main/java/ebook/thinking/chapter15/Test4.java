package ebook.thinking.chapter15;

/**
 * Created by Administrator on 206/2/27.
 */

/**
 * Java编程思想里的例子，用来演示自限定泛型的使用，同时本例也是一个很好的链式编程的例子
 */
public class Test4
{
    public static void main(String[] args)
    {
        Sub s = new Sub();
        System.out.println(s.set(new Sub()).get().getClass().getSimpleName());
        Derived d = new Derived();
        System.out.println(d.set(new Derived()).get().getClass().getSimpleName());
    }
}

class Base<T extends Base<T>>
{
    private T base;

    Base set(T t)
    {
        base = t;
        return this;
    }
    T get()
    {
        return base;
    }
}

class Sub extends Base<Sub>
{
}

class Derived extends Base<Derived> {}
