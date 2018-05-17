package ebook.designpattern.multiple.example1.factory;

import ebook.designpattern.multiple.example1.Quackable;

/**
 * 描述： <br>
 * 创建时间: 2017/7/1817:48 <br>
 *
 * @author 周志辉
 */
public abstract class AbstractDuckFactory {

    public abstract Quackable createMarllardDuck();

    public abstract Quackable createRedheadDuck();

    public abstract Quackable createDuckCall();

    public abstract Quackable createRubberDuck();

}
