package ebook.designpattern.prototype;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1010:38 <br>
 *
 * @author 周志辉
 */
public class Prototype implements Cloneable {
    public Prototype clone(){
        Prototype prototype = null;
        try{
            prototype = (Prototype)super.clone();
        }catch(CloneNotSupportedException e){
            e.printStackTrace();
        }
        return prototype;
    }
}