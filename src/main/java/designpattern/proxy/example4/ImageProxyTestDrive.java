package designpattern.proxy.example4;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

/**
 * 描述： HeadFirst设计模式中的虚拟代理模式例子<br>
 * 创建时间: 2017/5/3111:17 <br>
 *
 * @author 周志辉
 */
public class ImageProxyTestDrive {
    //ImageComponent找不到，代码也有部分缺失，initialURL,frame不知道哪定义的

    ImageComponent imageComponent;

    JFrame jFrame = new JFrame("CD Cover Viewer");

    JMenuBar menuBar;

    JMenu menu;

    Hashtable cds = new Hashtable();


    public static void main(String[] args) throws Exception {
        ImageProxyTestDrive testDrive = new ImageProxyTestDrive();
    }


    public ImageProxyTestDrive() throws Exception {
        cds.put("Ambient: Music for Airport", "http://images.amazon.com/images/P/");
        cds.put("Buddha Bar", "http://images.amazon.com/images/P/B00009XBYK.01.LZZZZZZZ.jpg");
        cds.put("", "");
        cds.put("", "");
        cds.put("", "");
        cds.put("", "");
        cds.put("", "");
        cds.put("", "");
        cds.put("", "");
        cds.put("", "");
        cds.put("", "");
        cds.put("", "");
        cds.put("", "");
        cds.put("", "");
        cds.put("", "");

        URL initialURL = new URL((String) cds.get("Buddha Bar"));
        menuBar = new JMenuBar();
        menu = new JMenu("Favorite CDs");
        menuBar.add(menu);
        jFrame.setJMenuBar(menuBar);

        Icon icon = new ImageProxy(new URL("http://baidu.com/"));
//        Icon icon = new ImageProxy(initialURL);
//        imageComponent = new ImageComponent(icon);
//        frame.getContentPane().add(imageComponent);
    }

    URL getCDUrl(String name) {
        try {
            return new URL((String) cds.get(name));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
