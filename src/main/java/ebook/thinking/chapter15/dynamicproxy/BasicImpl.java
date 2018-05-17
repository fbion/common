package ebook.thinking.chapter15.dynamicproxy;

/**
 * Created by Administrator on 2016/3/5.
 */
public class BasicImpl implements Basic {
    private String value;
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
            return this.value;
        }
}
