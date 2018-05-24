package ebook.designpattern.strategy.example2.behavior.impl;

import ebook.designpattern.strategy.example2.behavior.QuakeBehavior;

/**
 * 项目名称：zzh<br/>
 * *********************************<br/>
 * <p>类名称：MuteQuake</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/4/6 13:47<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/4/6 13:47<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public class MuteQuake implements QuakeBehavior {

    @Override
    public void quake() {
        System.out.println("<<silence>>");
    }
}