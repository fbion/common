package designpattern.proxy.example4;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 描述： HeadFirst设计模式中的虚拟代理模式例子<br>
 * 创建时间: 2017/5/3111:17 <br>
 *
 * @author 周志辉
 */
public class ImageProxyTestDrive {
    //ImageComponent找不到，代码也有部分缺失，initialURL,frame不知道哪定义的

//    ImageComponent imageComponent;


    public static void main(String[] args) {

    }


    public ImageProxyTestDrive() throws MalformedURLException {
        Icon icon = new ImageProxy(new URL("http://baidu.com/"));
//        Icon icon = new ImageProxy(initialURL);
//        imageComponent = new ImageComponent(icon);
//        frame.getContentPane().add(imageComponent);
    }
}
