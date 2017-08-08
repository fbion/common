package designpattern.chainofresponsibility.example1.test;

import designpattern.chainofresponsibility.example1.Handler;
import designpattern.chainofresponsibility.example1.concrete.DeptManager;
import designpattern.chainofresponsibility.example1.concrete.GeneralManager;
import designpattern.chainofresponsibility.example1.concrete.ProjectManager;

/**
 * 描述： <br>
 * 创建时间: 2017/8/815:17 <br>
 *
 * @author 周志辉
 */
public class Client {

    public static void main(String[] args) {
        //先要组装责任链
        Handler h1 = new GeneralManager();
        Handler h2 = new DeptManager();
        Handler h3 = new ProjectManager();
        h3.setNextHandler(h2);
        h2.setNextHandler(h1);

        //开始测试
        String test1 = h3.handleFeeRequest("张三", 300);
        System.out.println("test1 = " + test1);
        String test2 = h3.handleFeeRequest("李四", 300);
        System.out.println("test2 = " + test2);
        System.out.println("---------------------------------------");

        String test3 = h3.handleFeeRequest("张三", 700);
        System.out.println("test3 = " + test3);
        String test4 = h3.handleFeeRequest("李四", 700);
        System.out.println("test4 = " + test4);
        System.out.println("---------------------------------------");

        String test5 = h3.handleFeeRequest("张三", 1500);
        System.out.println("test5 = " + test5);
        String test6 = h3.handleFeeRequest("李四", 1500);
        System.out.println("test6 = " + test6);
    }

}