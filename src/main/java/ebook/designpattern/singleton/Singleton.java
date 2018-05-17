package ebook.designpattern.singleton;

/**
 * Created by zzh on 2015-12-06.
 */
public class Singleton
{
    private Singleton()
    {
        System.out.println("init");
    }

    public static Singleton getInstance()
    {
        return SingletonHolder.instance;
    }

    static class SingletonHolder
    {
        static Singleton instance = new Singleton();
    }
}
