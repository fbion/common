package ebook.designpattern.flyweight;

/**
 * 描述： 当需要大量树实例时会占用大量空间<br>
 *     可以改用一个实例，和一个用于维护众多实例状态的类来实现，这就是蝇量模式
 * 创建时间: 2017/8/815:26 <br>
 *
 * @author 周志辉
 */
public class Tree {

    private int  xCoord;

    private int yCoord;

    private int age;

    public void display() {
        System.out.println("Position is (" + xCoord + " , " + yCoord + " , age is " + age + ".");
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



}
