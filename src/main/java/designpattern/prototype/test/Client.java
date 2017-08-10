package designpattern.prototype.test;

import designpattern.prototype.ConcretePrototype;

/**
 * 描述： <br>
 * 创建时间: 2017/8/1010:39 <br>
 *
 * @author 周志辉
 */
public class Client {
    public static void main(String[] args){
        ConcretePrototype cp = new ConcretePrototype();
        for(int i=0; i< 10; i++){
            ConcretePrototype clonecp = (ConcretePrototype)cp.clone();
            clonecp.show();
        }
    }
}
