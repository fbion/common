package ebook.designpattern.proxy.example4;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * 描述： <br>
 * 创建时间: 2017/5/3110:56 <br>
 *
 * @author 周志辉
 */
public class ImageProxy implements Icon {

    ImageIcon imageIcon = new NullImageIcon();

    URL imageUrl;

    Thread retrievalThread;

    boolean retrieving = false;

    /**
     * 描述： <br>
     * 创建时间: 2017/5/3111:07 <br>
     *
     * @author 周志辉
     */
    public class NullImageIcon extends ImageIcon {

        @Override
        public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
            g.drawString("Loading CD cover, please wait...", x + 300, y + 190);
            if(!retrieving) {
                retrieving = true;
                retrievalThread = new Thread( () -> {
                    try {
                        imageIcon = new ImageIcon(imageUrl, "CD Cover");
                        c.repaint();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                retrievalThread.start();
            }
        }


        @Override
        public int getIconWidth() {
            return 800;
        }


        @Override
        public int getIconHeight() {
            return 600;
        }
    }


    public ImageProxy(URL imageUrl) {
        this.imageUrl = imageUrl;
    }


    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        imageIcon.paintIcon(c, g, x, y);
    }


    @Override
    public int getIconWidth() {
        return imageIcon.getIconWidth();
    }


    @Override
    public int getIconHeight() {
        return imageIcon.getIconHeight();
    }
}
