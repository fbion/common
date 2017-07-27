package designpattern.multiple.example1.decorator;

import designpattern.multiple.example1.Quackable;

import java.io.UnsupportedEncodingException;

/**
 * 描述： <br>
 * 创建时间: 2017/7/1817:41 <br>
 *
 * @author 周志辉
 */
public class QuackCounter implements Quackable {

    Quackable duck;

    static int numberOfQuacks;


    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }


    @Override
    public void quack() {
        duck.quack();
        numberOfQuacks++;
    }

    public static int getQuacks() {
        return numberOfQuacks;
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
//        System.out.println(URLEncoder.encode(new String("你好  ".getBytes("ISO8859-1"), "ISO8859-1")));
//        System.out.println(URLDecoder.decode("%E4%BD%A0%E5%A5%BD++", "UTF-8"));

        System.out.println(new String("你好  ".getBytes("UTF-8"), "ISO8859-1"));
    }
}
