package ebook.thinking.chapter17;

/**
 * Created by Administrator on 2016/3/19.
 */
public abstract class Test<C> {
    //保存操作名
    String name;
    public Test(String name){
        this.name = name;
    }

    /**
     * 实现具体测试操作
     * @param container
     * @param tp
     * @return
     */
    abstract int test(C container, TestParam tp) ;
}
